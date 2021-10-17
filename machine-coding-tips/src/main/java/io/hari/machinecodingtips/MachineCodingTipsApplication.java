package io.hari.machinecodingtips;

import io.hari.machinecodingtips.modelmapper.MyService;
import io.hari.machinecodingtips.validation.EntityA;
import io.hari.machinecodingtips.validation.MyServiceNewStyle;
import io.hari.machinecodingtips.validation.MyServiceOldStyle;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.*;

@SpringBootApplication
@RequiredArgsConstructor
public class MachineCodingTipsApplication implements CommandLineRunner {
    private final MyServiceOldStyle myServiceOldStyle;
    private final MyServiceNewStyle myServiceNewStyle;
    private final MyService myService;

    public static void main(String[] args) {
        SpringApplication.run(MachineCodingTipsApplication.class, args);
        ModelMapper modelMapper = new ModelMapper();
    }

    @Override
    public void run(String... args) throws Exception {
    	//test one at a time, comment others

//		test1_validationAtEveryLevel();

//		test2_modelMapperEntityConversion();

		test3_collectionCanStoreListSet();
	}

	private void test3_collectionCanStoreListSet() {
		Collection<String> collection;
		List<String> list = new ArrayList<>();
		list.add("h");
		list.add("h");
		list.add("h");
		collection = list;
		System.out.println("collection object can store list = " + list);

		Set<String> set = new HashSet<>();
		set.add("h");
		set.add("a");
		set.add("y");

		collection = set;
		System.out.println("collection object can store set = " + set);
	}

	private void test2_modelMapperEntityConversion() {
		myService.foo();
	}

	private void test1_validationAtEveryLevel() {
		EntityA hariom = EntityA.builder()
				.name("")
				.email("hari@gmaul.com")
				.phoneNumber(BigInteger.valueOf(9887700499L))
				.dateOfBirth(LocalDate.of(1989, 06, 26)).build();
		System.out.println("hariom = " + hariom);

		//old impl
		myServiceOldStyle.stringMethod(hariom);//validate all Entity annotation, that have no group mentioned, it will only validate date

		//new impl
		myServiceNewStyle.stringMethod(hariom);//only string group validation
		myServiceNewStyle.numberMethod(hariom);//only number group validation
	}
}
