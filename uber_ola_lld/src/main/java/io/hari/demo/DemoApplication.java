package io.hari.demo;

import io.hari.demo.config.AppConfig;
import io.hari.demo.dao.CabDao;
import io.hari.demo.dao.UserDao;
import io.hari.demo.entity.Cab;
import io.hari.demo.entity.Location;
import io.hari.demo.entity.Trip;
import io.hari.demo.entity.User;
import io.hari.demo.services.CabService;
import io.hari.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	AppConfig config;

	@Autowired
	UserService userService;
	@Autowired
	UserDao userDao;
	@Autowired
	CabDao cabDao;
	@Autowired
	CabService cabService;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("config = " + config);

		//todo done :add user
		User user = User.builder().name("hariom")
				.location(Location.builder().x(Double.valueOf(0)).y(Double.valueOf(0)).build()).build();
		userDao.save(user);
		final Double userSearchRadius = Double.valueOf(2);
		//todo done : add cabs
		Cab cab1 = Cab.builder().status("available").location(Location.builder().x(Double.valueOf(0)).y(Double.valueOf(0)).build()).build();
		cabService.addCab(cab1);

		//todo done :add cab on diagonal
		Cab cab2 = Cab.builder().status("available").location(Location.builder().x(Double.valueOf(1)).y(Double.valueOf(1)).build()).build();
		cabService.addCab(cab2);

		//add cab on on x axis
		Cab cab3 = Cab.builder().status("available").location(Location.builder().x(Double.valueOf(1)).y(Double.valueOf(0)).build()).build();
		cabService.addCab(cab3);

		//todo done : search cabs - within radius
		final List<Cab> availableCabs = userService.findAvailableCabs(user, userSearchRadius);
		System.out.println("availableCabs = " + availableCabs);

		//todo done :search cabs - out of radius
		final List<Cab> availableCabs2 = userService.findAvailableCabs(user, Double.valueOf(1));
		System.out.println("out of radius availableCabs = " + availableCabs2);

		final List<Cab> availableCabs3 = userService.findAvailableCabs(user, Double.valueOf(100));
		System.out.println("max radius availableCabs = " + availableCabs3);

		//todo done : book a trip
		final Location toLocation = Location.builder().x(Double.valueOf(2)).y(Double.valueOf(2)).build();
		final Trip trip = userService.bookTrip(user, availableCabs, toLocation);
		System.out.println("trip = " + trip);

		//todo done : after booking a trip, find available cabs - result should not contain booked cab
		final List<Cab> availableCabs1 = userService.findAvailableCabs(user, userSearchRadius);
		System.out.println("availableCabs1 = " + availableCabs1);
		//availableCabs1 = [Cab(super=BaseEntity(id=2), status=available, location=Location(x=1.0, y=1.0)), Cab(super=BaseEntity(id=3), status=available, location=Location(x=1.0, y=0.0))]

		//todo : change cab status to not available and find all cabs
		cabService.changeCabStatus(cab3, "sleeping");
		final List<Cab> availableCabs4 = userService.findAvailableCabs(user, userSearchRadius);
		System.out.println("availableCabs4 = " + availableCabs4);
		//availableCabs4 = [Cab(super=BaseEntity(id=2), status=available, location=Location(x=1.0, y=1.0))]

		//todo change cab location and find available cabs
		cabService.changeCabLocation(cab2, Location.builder().x(Double.valueOf(10)).y(Double.valueOf(10)).build());
		final List<Cab> availableCabs5 = userService.findAvailableCabs(user, userSearchRadius);
		System.out.println("availableCabs5 = " + availableCabs5);
		//availableCabs5 = []

		//todo done :fetch user history
		final List<Trip> trips = userService.fetchTripsHistory(user.getId());
		System.out.println("trips = " + trips);

		//todo done : fetch cab history - valid entry
		final List<Trip> cabHistory = cabService.fetchCabHistory(cab1.getId());
		System.out.println("cabHistory = " + cabHistory);

		//todo done : fetch cab history - no entry
		final List<Trip> cabHistory2 = cabService.fetchCabHistory(cab2.getId());
		System.out.println("cabHistory = " + cabHistory2);

		//end trip and change the cab status back to available
		Thread.sleep(1000*40);
		cabService.endTrip(trip);

	}
}
