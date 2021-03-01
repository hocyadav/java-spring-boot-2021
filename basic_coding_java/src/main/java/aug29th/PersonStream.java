package aug29th;

import java.util.Arrays;import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class PersonStream {

	public static void main(String[] args) {
		List<Person> allPerson = getAllPerson();

		Map<Boolean, List<Person>> collect = 
				allPerson.stream()//stream of list
				.collect(Collectors.partitioningBy(new Predicate<Person>() {//predicates -> t/f
					public boolean test(Person t) {
						return t.getCountry().equals("india");//true condition : this is KEY for partitioning By 
					}//VALUE : is input stream of list that is coming as this collect
				}));
		System.out.println("collect \n"+collect);


		Map<Boolean, List<Person>> collect2 = 
				allPerson.stream()
				.collect(Collectors.partitioningBy((Person t) -> {
					return t.getCountry().equals("india");//true condition
				}
						));
		System.out.println("collect2 \n"+collect2);

		Map<Boolean, List<Person>> collect3 = 
				allPerson.stream()
				.collect(Collectors.partitioningBy(t -> {
					return t.getCountry().equals("india");//true condition
				}
						));
		System.out.println("collect3 \n"+collect3);

		Map<Boolean, List<Person>> collect4 = 
				allPerson.stream()
				.collect(Collectors.partitioningBy( t -> t.getCountry().equals("india") ));
		System.out.println("collect4 \n"+collect4);


		//IMP
		Map<Boolean, List<Person>> collect5 = 
				allPerson//List of Person
				.stream()//STREAM of Person - coming from List of Person , So below group by value will be List of person
				.collect(Collectors.groupingBy( t -> t.getCountry().equals("india") ));//KEY is boolean here, VALUE is above stream input
		System.out.println("collect5 \n"+collect5);

		//IMP
		Set<Person> set = new HashSet<Person>(allPerson);
		Map<String, List<Person>> collect6__ = 
				set//SEt of person 
				.stream()//Stream of person 
				.collect(Collectors.groupingBy(t -> t.getCountry()));//this return value as list of person , but our input is Set of person

		//USE case : if we want to store as set as original then use 2nd argument of grouping by
		Map<String, Set<Person>> collect7___ = 
				set//SEt of person 
				.stream()//Stream of person 
				.collect(Collectors.groupingBy(t -> t.getCountry(), Collectors.toSet()));//this return value as list of person , but our input is Set of person


		Map<String, List<Person>> collect6 = 
				allPerson.stream()
				.collect(Collectors.groupingBy(t -> t.getCountry()));//KEY is country, VALUE is above stream
		System.out.println("collect6 \n"+collect6);

		Map<Person, List<Person>> collect7 = 
				allPerson.stream()
				//.collect(Collectors.groupingBy(t -> t));//KEY is country, VALUE is above stream
				.collect(Collectors.groupingBy(Function.identity()));//same as above
		System.out.println("collect7 \n"+collect7);

		//------------

		Map<Boolean, Long> collect8 = 
				allPerson.stream()
				.collect(Collectors.partitioningBy( 
						t -> t.getCountry().equals("india") , //KEY as T/F : since this is predicate
						Collectors.counting()//VALUE, erlier value coming as above stream input 
						));
		System.out.println("collect8 \n"+collect8);

		Map<Boolean, List<Person>> collect9 = 
				allPerson.stream()
				.collect(Collectors.partitioningBy( 
						t -> t.getCountry().equals("india") , //KEY as T/F : since this is predicate
						Collectors.toList()//VALUE, erlier value coming as above stream input 
						));
		System.out.println("collect9 \n"+collect9);

		Map<String, List<Person>> collect10 = 
				allPerson.stream()
				.collect(Collectors.groupingBy( 
						t -> t.getCountry(), //KEY as function, return t/f or return name or anything
						Collectors.toList()//VALUE, erlier value coming as above stream input 
						));
		System.out.println("collect10 \n"+collect10);

		//---------

		Map<Boolean, List<String>> collect11 = 
				allPerson.stream()
				.collect(Collectors.partitioningBy(t -> t.getName().equals("hari"),//partition 1st part predicate
						Collectors.mapping(//VALUE - 2nd part
								p -> p.getCountry().toUpperCase(), //4th value content is country
								Collectors.toList())//3rd value type is list
						));
		System.out.println(collect11);

		Map<Boolean, Set<String>> collect12 = 
				allPerson.stream()
				.collect(Collectors.partitioningBy(t -> t.getName().equals("hari"),//partition 1st part predicate
						Collectors.mapping(//VALUE - 2nd part
								p -> p.getCountry().toUpperCase(), //4th value content is country
								Collectors.toSet())//3rd value type is list
						));
		System.out.println(collect12);

		//now do above with groupby + extra thing we can do with group by is that KEY may be our type
		Map<Boolean, List<String>> collect13 = 
				allPerson.stream()
				.collect(Collectors.groupingBy(t -> t.getName().equals("hari"),//partition 1st part predicate
						Collectors.mapping(//VALUE - 2nd part
								p -> p.getCountry().toUpperCase(), //4th value content is country
								Collectors.toList())//3rd value type is list
						));
		System.out.println(collect13);//same output as 11


		Map<Boolean, Set<String>> collect14 =
				allPerson.stream()
				.collect(Collectors.groupingBy(t -> t.getName().equals("hari"),//partition 1st part predicate
						Collectors.mapping(//VALUE - 2nd part
								p -> p.getCountry().toUpperCase(), //4th value content is country
								Collectors.toSet())//3rd value type is list
						));
		System.out.println(collect14);//same output as 12 

		//change key as our own type 

		Map<String, List<String>> collect15 = 
				allPerson.stream()
				.collect(Collectors.groupingBy(t -> t.getCountry(),//partition 1st KEY
						Collectors.mapping(//VALUE - 2nd part
								p -> p.getName().toUpperCase(), //4th value content is country
								Collectors.toList())//3rd value type is list
						));
		System.out.println(collect15);//same output as 11

		//using set
		Map<String, Set<String>> collect16 = 
				allPerson.stream()
				.collect(Collectors.groupingBy(t -> t.getCountry(),//partition 1st part predicate
						Collectors.mapping(//VALUE - 2nd part
								p -> p.getName().toUpperCase(), //4th value content is country
								Collectors.toSet())//3rd value type is set
						));
		System.out.println(collect16);//same output as 12 


	}
	
	/**
	 * 
collect 
{false=[chandan, swarn, rajat, om, hariom, omprakash], true=[hari, neha, op, op]}
collect2 
{false=[chandan, swarn, rajat, om, hariom, omprakash], true=[hari, neha, op, op]}
collect3 
{false=[chandan, swarn, rajat, om, hariom, omprakash], true=[hari, neha, op, op]}
collect4 
{false=[chandan, swarn, rajat, om, hariom, omprakash], true=[hari, neha, op, op]}
collect5 
{false=[chandan, swarn, rajat, om, hariom, omprakash], true=[hari, neha, op, op]}
collect6 
{brazil=[chandan, swarn], canada=[hariom, omprakash], india=[hari, neha, op, op], us=[rajat, om]}
collect7 
{rajat=[rajat], op=[op], hariom=[hariom], om=[om], chandan=[chandan], op=[op], hari=[hari], swarn=[swarn], omprakash=[omprakash], neha=[neha]}
collect8 
{false=6, true=4}
collect9 
{false=[chandan, swarn, rajat, om, hariom, omprakash], true=[hari, neha, op, op]}
collect10 
{brazil=[chandan, swarn], canada=[hariom, omprakash], india=[hari, neha, op, op], us=[rajat, om]}
{false=[INDIA, INDIA, INDIA, BRAZIL, BRAZIL, US, US, CANADA, CANADA], true=[INDIA]}
{false=[CANADA, INDIA, BRAZIL, US], true=[INDIA]}
{false=[INDIA, INDIA, INDIA, BRAZIL, BRAZIL, US, US, CANADA, CANADA], true=[INDIA]}
{false=[CANADA, INDIA, BRAZIL, US], true=[INDIA]}
{brazil=[CHANDAN, SWARN], canada=[HARIOM, OMPRAKASH], india=[HARI, NEHA, OP, OP], us=[RAJAT, OM]}
{brazil=[SWARN, CHANDAN], canada=[OMPRAKASH, HARIOM], india=[HARI, OP, NEHA], us=[RAJAT, OM]}

	 * @return
	 */

	public static List<Person> getAllPerson() {
		return Arrays.asList(
				new Person("hari", "india"),
				new Person("neha", "india"),
				new Person("op", "india"),
				new Person("op", "india"),

				new Person("chandan", "brazil"),
				new Person("swarn", "brazil"),

				new Person("rajat", "us"),
				new Person("om", "us"),

				new Person("hariom", "canada"),
				new Person("omprakash", "canada")
				);

	}
}
