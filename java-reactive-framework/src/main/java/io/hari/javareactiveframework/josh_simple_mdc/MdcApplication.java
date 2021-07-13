package io.hari.javareactiveframework.josh_simple_mdc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Signal;
import reactor.util.context.Context;

import java.util.Collection;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
	* @author <a href="mailto:josh@joshlong.com">Josh Long</a>
	*/
@SpringBootApplication
@Log4j2
@RestController
public class MdcApplication {

	private final RestaurantService restaurantService;

	public MdcApplication(RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
	}

	public static void main(String[] args) {
		SpringApplication.run(MdcApplication.class, args);
	}

	@GetMapping("/{uid}/restaurants/{price}")
	Flux<Restaurant> restaurantFlux(@PathVariable String uid, @PathVariable Double price) {
		return adaptResults(this.restaurantService.getByMaxPrice(price), uid, price);
	}

	Flux<Restaurant> adaptResults(Flux<Restaurant> in, String uid, double price) {
		return Mono.just(String.format("finding restaurants having price lower than $%.2f for %s", price, uid))
			.doOnEach(logOnNext(log::info))
			.thenMany(in)
			.doOnEach(logOnNext(r -> log.info("found restaurant {} for ${}", r.getName(), r.getPricePerPerson())))
			.subscriberContext(Context.of(UID, uid));
	}

	private final static String UID = "uid";

	private static <T> Consumer<Signal<T>> logOnNext(Consumer<T> logStatement) {

		return signal -> {
			if (!signal.isOnNext()) {
				return;
			}

			Optional<String> uidOptional_fromContext = signal.getContext().getOrEmpty(UID);
			Runnable orElse = () -> logStatement.accept(signal.get());

			Consumer<String> ifPresent = uid -> {
				try (MDC.MDCCloseable closeable = MDC.putCloseable(UID, uid)) {
					orElse.run();
				}
			};

			uidOptional_fromContext.ifPresentOrElse(ifPresent, orElse);//Consumer, Runnable
		};
	}
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Restaurant {
	private double pricePerPerson;
	private String name;
}

@Service
class RestaurantService {

	private final Collection<Restaurant> restaurants = new ConcurrentSkipListSet<>((o1, o2) -> {
		Double one = o1.getPricePerPerson();
		Double two = o2.getPricePerPerson();
		return one.compareTo(two);
	});

	RestaurantService() {
		IntStream.range(0, 1000)
			.mapToObj(Integer::toString)
			.map(i -> "restaurant # " + i)
			.map(str -> new Restaurant(new Random().nextDouble() * 100, str))
			.forEach(this.restaurants::add);
	}


	Flux<Restaurant> getByMaxPrice(double maxPrice) {

		Stream<Restaurant> res = this.restaurants.parallelStream()
			.filter(restaurant -> restaurant.getPricePerPerson() <= maxPrice);

		return Flux.fromStream(res);

	}
}
