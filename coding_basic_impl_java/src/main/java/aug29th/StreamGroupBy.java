package aug29th;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamGroupBy {
	public static void main(String[] args) {
		Stream<String> stream = Stream.of("apple", "apple", "mangoes", "cat", "dog", "cat", "dog", "dog");
		Map<String, List<String>> collect = stream.collect(Collectors.groupingBy(word->word));
		System.out.println(collect);

		List<String> asList = Arrays.asList("apple", "apple", "mangoes", "cat", "dog", "cat", "dog", "dog");
		Map<String, List<String>> collect2 = 
				asList.stream()
				.collect(Collectors.groupingBy(word -> word, Collectors.toList()));
		System.out.println(collect2);


		Map<String, Long> collect3 = 
				asList.stream()
				.collect(Collectors.groupingBy(
						word -> word, //Key :  is same as word, or we can use Function.identity()
						Collectors.counting()));//Value : count
		System.out.println(collect3);

		//		Map<String, Map<String, String>> collect4 = 
		//				asList.stream()
		//				.collect(Collectors.groupingBy(
		//						word -> word, //Key :  is same as word, or we can use Function.identity()
		//						Collectors.toMap(word->word, word->word)));//Value : count
		//		System.out.println(collect4);
		//		

		asList.stream()
		.filter(StreamGroupBy::check1)//functional style
		.forEach(System.out::println);
		//https://www.youtube.com/watch?v=yAgp10gP5uQ&list=PLgjd7_JBgEE7oL1SHWVGzwKHO-XMhwArl&index=23&t=0s
		asList.stream()
		.filter(t -> !StreamGroupBy.check1(t))//negative above condition 
		.forEach(System.out::println);

		//IMP : order of method invocation is imp

		List<String> collect4 = asList.stream()
				.collect(Collectors.toList());

		String collect5 = 
				asList.stream()
				.collect(Collectors.joining(","));
		System.out.println(collect5);

		//		asList.stream()
		//		.collect(Collectors.toCollection(TreeSet::new);
		//		

		HashSet<String> hset = 
				asList.stream()
				.collect(Collectors.toCollection(HashSet::new));
		System.out.println(hset);

		TreeSet<String> collect6 = 
				asList.stream()
				.collect(Collectors.toCollection(TreeSet::new));
		System.out.println(collect6);

		ArrayList collect7 = asList.stream()
				.collect(Collectors.toCollection(new Supplier<ArrayList>() {
					public ArrayList get() {
						return new ArrayList<String>();
					}
				}));
		System.out.println(collect7);

		HashSet collect8 = 
				asList.stream()
				.collect(Collectors.toCollection(new Supplier<HashSet>() {
					public HashSet get() {
						return new HashSet<String>();
					}
				}));
		System.out.println(collect8);

		Map<Boolean, List<String>> collect9 = 
				asList.stream()
				.collect(Collectors.partitioningBy(t -> t.length() > 4));//map of Treu and false
		System.out.println("map with T/F --> "+collect9);

		//from above get true element list

		List<String> list = 
				asList.stream()
				.collect(Collectors.partitioningBy(t -> t.length() > 4))//this is map of predicate i.e. 2 key True and another is false
				.get(true);
		System.out.println("true list --> "+list);
		
		List<String> list2 = 
				asList.stream()
				.collect(Collectors.partitioningBy(t -> t.length() > 4))//this is map
				.get(false);
		System.out.println("false list --> "+list2);


	}
	

	public static boolean check1(String str) {
		if (str.length() > 4) return true;
		return false;
	}

}
/**
 * 
{apple=[apple, apple], cat=[cat, cat], mangoes=[mangoes], dog=[dog, dog, dog]}
{apple=[apple, apple], cat=[cat, cat], mangoes=[mangoes], dog=[dog, dog, dog]}
{apple=2, cat=2, mangoes=1, dog=3}
apple
apple
mangoes
cat
dog
cat
dog
dog
apple,apple,mangoes,cat,dog,cat,dog,dog
[apple, cat, mangoes, dog]
[apple, cat, dog, mangoes]
[apple, apple, mangoes, cat, dog, cat, dog, dog]
[apple, cat, mangoes, dog]
map with T/F --> {false=[cat, dog, cat, dog, dog], true=[apple, apple, mangoes]}
true list --> [apple, apple, mangoes]
false list --> [cat, dog, cat, dog, dog]

 * 
 */
