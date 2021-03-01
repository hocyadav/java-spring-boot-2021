package june26;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class TestSupplier implements Supplier<String>{

	@Override
	public String get() {
		return "hariom";
	}
	public static void main(String[] args) {
		//old way
		TestSupplier supplier = new TestSupplier();
		System.out.println(supplier.get());

		//new way
		Supplier<String> s = () -> {
			return "hariom yadav";
		};
		//System.out.println(s.get());

		List<String> l2 = Arrays.asList("a","b");
		
		Optional<String> optional = l2.stream().findAny();//find any return optional
		System.out.println(optional.isPresent());//true
		String orElseGet = optional.orElseGet(s);//not call s since list is not empty
		System.out.println(" - "+orElseGet);
		
		
		List<String> l3 = Arrays.asList();//empty  list
		Optional<String> optional2 = l3.stream().findAny();//find any return optional
		System.out.println(optional2.isPresent());//false since list is empty , so we can call orElseGet and pass supplier obj
		String orElseGet2 = optional2.orElseGet(s);//call supplier obj since optional return false
		System.out.println(" - "+orElseGet2);
		

	}
}
