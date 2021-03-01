package aug29th;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class STreamToMap {
	public static void main(String[] args) {
		List<String> list = 
				Stream.of("hari", "om", "yadav")//stream of String
				.map(t -> t.toUpperCase())
				.collect(Collectors.toList());//list of String
		System.out.println(list);


		Set<String> set = 
				Stream.of("hari", "om", "yadav")//stream of String
				.map(t -> t.toUpperCase())
				.collect(Collectors.toSet());//set of String
		System.out.println(set);

		Map<String, String> map = 
				Stream.of("hari", "om", "yadav")//stream of String
				.collect(Collectors.toMap(t -> t.toString(), t -> t));
		System.out.println(map);

		Map<String, String> map2 = 
				Stream.of("hari", "om", "yadav")//stream of String
				.collect(Collectors.toMap(t -> t.toString(), Function.identity()));
		System.out.println(map2);


		Map<String, List<String>> collect = 
				Stream.of("hari", "om", "yadav")
				.collect(Collectors.groupingBy(new Function<String, String>() {
					public String apply(String t) {
						return t+"__";
					}
				}));
		System.out.println(collect);

		//		
		//		
		//		final Map<String, Product> productMap = catalogGatewayService
		//		        .multiGetProducts(order.getItems().stream()//set of items
		//		                               .map(OrderItem::getListingId)
		//		                               .collect(Collectors.toSet()))
		//		        .stream() //Stream of products
		//		        .collect(Collectors.toMap(Product::getExternalId, Function.identity()));


	}

}
