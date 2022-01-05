package io.hari.machinecodingtips;

import com.google.common.base.Function;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import io.hari.machinecodingtips.modelmapper.MyService;
import io.hari.machinecodingtips.validation.EntityA;
import io.hari.machinecodingtips.validation.MyServiceNewStyle;
import io.hari.machinecodingtips.validation.MyServiceOldStyle;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.*;

@Getter @Setter
@Builder
@NoArgsConstructor @AllArgsConstructor
class MyCLass {
	String name;
}

@Getter @Setter
@Builder
@NoArgsConstructor @AllArgsConstructor
class MyCLass2 {
	String name;
}
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
		googleMapsMethod();
	}

	private void googleMapsMethod() {
		//transfer map value to some other type
		Map<String, Integer> initialMap = new HashMap<>();
		Map<String, MyCLass> finalMap = Maps.transformEntries(initialMap, new Maps.EntryTransformer<String, Integer, MyCLass>() {
			@Override
			public MyCLass transformEntry(String key, Integer value) {//t1(key) t2(value)
				//do processing or conversion and convert to t3 using t1 t2
				//here key will be use in case we need to call other services and get data whereas the transformValues() only takes the value and it will mappper to another type
				return MyCLass.builder().build();//t3
			}
		});

		Map<String, MyCLass> finalMap2 = Maps.transformValues(initialMap, new Function<Integer, MyCLass>() {//t1(map value) to t2(map value)
			@Override
			public MyCLass apply(Integer integer) {
				return MyCLass.builder().build();
			}
		});

		Map<String, MyCLass> inputMap1 = new HashMap<>();
		Map<String, MyCLass2> outputMap = Maps.transformValues(inputMap1, new Function<MyCLass, MyCLass2>() {//t1(old value) to t2(new value)
			@Override
			public MyCLass2 apply(MyCLass myCLass) {
				return MyCLass2.builder().build();
			}
		});

		//Maps.transformValues() input map + mapper Function interface// mapper will convert the t1 to t2, t1 is old value and t2 is new value
		//Maps.transformEntries() input map + mapper BiFunction interface// only extra key will be the input for the mapper, else everything is same

		MapDifference<String, Integer> mapDifference = Maps.difference(Map.of("one", 1, "two", 2), Map.of("two", 2, "three", 3));
		System.out.println("mapDifference = " + mapDifference);
		Map<String, Integer> left = mapDifference.entriesOnlyOnLeft();
		System.out.println("left = " + left);
		Map<String, Integer> right = mapDifference.entriesOnlyOnRight();
		System.out.println("right = " + right);
		Map<String, Integer> common = mapDifference.entriesInCommon();
		System.out.println("common = " + common);
		Map<String, MapDifference.ValueDifference<Integer>> valueDifferenceMap = mapDifference.entriesDiffering();
		System.out.println("valueDifferenceMap = " + valueDifferenceMap);

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
