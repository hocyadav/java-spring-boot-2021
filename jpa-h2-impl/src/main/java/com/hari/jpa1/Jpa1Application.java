package com.hari.jpa1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class Jpa1Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Jpa1Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


        String value = "hariom";

        Optional.of(value);//this is used inside optional of nullable, when value is not null
        Optional.empty(); // this is also used inside optional of nullable, when value is null

        Optional.ofNullable(value);//this contains Optional of(value) or optional of empty

        //todo : impl
        final Optional<String> stringOptional = Optional.ofNullable("hariom");//this is same as optional.of(value)
        final Optional<String> hariom = Optional.of("hariom"); // above code will sent output like this
        System.out.println("stringOptional = " + stringOptional);
        System.out.println("hariom = " + hariom);

        final Optional<String> stringOptional2 = Optional.ofNullable(null);//this is same as optional.empty()
        final Optional<Object> empty = Optional.empty(); // above code will give output like this,
        System.out.println("stringOptional2 = " + stringOptional2);
        System.out.println("empty = " + empty);
        //Todo : conclusion : optional.of nullable calls then it will call internally 2 things
//		internal impl of optional of nullable
//		public static <T> Optional<T> ofNullable(T value) {
//			return value == null
//					? empty() //if value null call empty optional
//					: of(value); //if value present call of optional
//		}

        //todo : optional others method impl
        Student student = new Student(123, "hariom");
//		Student student = null;
        Student student2 = new Student(123, "hariom");
//		Student student2 = null;
//		normal student equal check - this is expected behavior
        System.out.println("student.equals(student2) = " + student.equals(student2));//this line will break if any of above is null

        final Optional<Student> student1Optional = Optional.ofNullable(student);
        //m1
        final boolean equals = student1Optional.equals(Optional.of(student2));//equals will take optional object
        System.out.println("m1 equals = " + equals);

        //m2 https://stackoverflow.com/questions/37004579/convenient-way-of-checking-equality-for-optionals
        final boolean check = student1Optional.isPresent() && student1Optional.get().equals(student2);
        System.out.println("m2 check = " + check);
        //m3
        final boolean equals2 = student2.equals(student1Optional.orElse(null));
        System.out.println("m3 equals2 = " + equals2);
        //m4
        final boolean equals23 = student2.equals(student1Optional.orElseGet(() -> new Student()));
        System.out.println("m4 equals23 = " + equals23);

        //after filter we will get optional, optional -> filter -> optional
        final Optional<Student> optional = student1Optional.filter(i -> i.getName().equals("hariom"));//gives new optional
        //optional -> map -> optional of map output
        final Optional<String> optional1 = student1Optional.map(i -> i.getName());
        final Optional<String> optionalS = student1Optional.flatMap(i -> Optional.of(i.getName()));//same as above but here we can pass stream of stream as input

        // ? can we stream oven optional
//		final Optional<List<Student>> students1 = Optional.ofNullable(Arrays.asList(student, student2));
//		Optional.ofNullable(null).orElseThrow(() -> new RuntimeException("value is null..."));//working
//		Optional.ofNullable("value").orElseThrow(() -> new RuntimeException("value is null..."));//working


        final Optional<Integer> integer = Optional.of(123);
        final Optional<Integer> integer2 = Optional.of(123);
        final boolean equals1 = integer.equals(integer);
        System.out.println("equals1 = " + equals1);

        //todo : streams
        streamFun();
    }

    private void streamFun() {
        int[] arr = new int[]{1, 2, 3, 4};
        List<Integer> list = Arrays.asList(1, 2, 3, 4);

        //convert array to stream :
        // primitive type,
        final Stream<int[]> stream = Stream.of(arr);
        // collection type
        final Stream<Integer> stream1 = list.stream();//stream() method is added to Collection interface

        //using stream own builder method
        Stream.Builder<Integer> builder = Stream.builder();//step 1 create builder obj of Integer
        final Stream<Integer> stream2 = builder.add(1).add(2).add(3).build();// add data to builder

        //for each(terminal operation) + peek (non terminal)
        list.stream().peek(i -> System.out.println(i))//non terminal
                .forEach(System.out::println);//terminal

        //High Level :  stream -> map -> stream
        final Stream<String> stringStream = list.stream()//integer stream
                .map(i -> i.toString());//string stream

        //High level : collect (get out of stream)
        final List<Integer> collect = stream1.collect(Collectors.toList());//uses Collectors interface, see for more info

        //Collectors interface
        //method 1: average : 3 method - averagingDouble(), averagingLong(), averagingInt()
        final Double avgValue = list.stream()//integer stream
                .collect(Collectors.averagingInt(i -> i.intValue()));//int avg
        System.out.println("avgValue = " + avgValue);

        Student student = new Student(75, "chandan"); student.setStudentType(StudentType.btech);
        Student student2 = new Student(25, "hari"); student2.setStudentType(StudentType.mtech);
        Student student3 = new Student(25, "hari"); student3.setStudentType(StudentType.btech);//hari is in btech and mtech type

        final Double rollNumAvg = Stream.of(student, student2)
                .collect(Collectors.averagingInt(i -> i.getRollNumber()));//int avg
        System.out.println("rollNumAvg = " + rollNumAvg);

        //method 2 : count stream
        final Long countStream = Stream.of(student, student2).collect(Collectors.counting());//internal it will call reducing
        System.out.println("countStream = " + countStream);

        final Integer reduce = Stream.of(student, student2).map(i -> i.getRollNumber())//integer stream
                .reduce(0, (a, b) -> a + b);
        System.out.println("sum of all roll num = " + reduce);

        final String reduce1 = Stream.of("hari", "om", "yadav").reduce("", (a, b) -> a + b);
        System.out.println("sum of all strings = " + reduce1);

        final BigDecimal reduce2 = Stream.of(BigDecimal.valueOf(75.01), BigDecimal.valueOf(25.20)).parallel().reduce(BigDecimal.valueOf(0), (a, b) -> a.add(b));
        System.out.println("reduce2 = " + reduce2);

        Stream.of(1, 2, 3).reduce(0, (a, b) -> {
											try {
												return a / b;
											} catch (ArithmeticException e) {
												System.out.println("e.getStackTrace() = " + e.getStackTrace());
											}
											return 0;
										});

        //method 3 : collectors joining 3 method : goal output as single string
		final String collect1 = Stream.of("hari", "om", "yadav").collect(Collectors.joining());
		System.out.println("merge all string = " + collect1);
		final String collect3 = Stream.of("hari", "om", "yadav").collect(Collectors.joining(","));//many use case, in above reduce we can get delimiter after last string
		System.out.println("collect3 = " + collect3);
		final String collect4 = Stream.of("hari", "om", "yadav")
							.collect(Collectors.joining(", ", "Flipkart hariom(fetch from db) user: "," Activate Now >"));//this is used in flipkart notification msg
		//prefix and suffix added only once
		System.out.println("collect4 = " + collect4); // Flipkart hariom(fetch from db) user: hari, om, yadav Activate Now >

		//both below are same : https://stackoverflow.com/questions/40826431/whats-the-difference-between-groupingby-and-mapping-in-collectors-java
		final Collector<Integer, ?, List<Integer>> c = Collectors.toList();
		final Function<Student, Integer> m = i -> i.getRollNumber();

		final List<Integer> collect2 = Stream.of(student, student2).map(m).collect(c);
		final List<Integer> collect5 = Stream.of(student, student2).collect(Collectors.mapping(m, c));
		System.out.println("collect2 = " + collect2); //[75, 25]
		System.out.println("collect5 = " + collect5); //[75, 25]

		//grouping by 3 different method : https://stackoverflow.com/questions/40826431/whats-the-difference-between-groupingby-and-mapping-in-collectors-java
		//m1 : key : map (we are passing), value as list (default collectors interface method here) and this will contain Obj from previous stream
		final Map<Integer, List<Student>> collect6 = Stream.of(student, student2)
												.collect(Collectors.groupingBy(m));//map obj : takes mapper, that will become key and value will automatically be list
		System.out.println("collect6 = " + collect6);
//        {25=[Student{rollNumber=25, name='hari'}], 75=[Student{rollNumber=75, name='chandan'}]}

		//m2 : same as above , but here we can change to set also
		final Map<Integer, List<Student>> collect7 = Stream.of(student, student2)
												.collect(Collectors.groupingBy(m, //map obj : take map as key
														Collectors.toList()));    //collectors interface obj : take value what we want , list or set
		System.out.println("collect7 = " + collect7);
//        {25=[Student{rollNumber=25, name='hari'}], 75=[Student{rollNumber=75, name='chandan'}]}

		//m3 : in that collectors interface if we want other getter not that whole obj in list
		// then we can replace collectors interface above with anotehr collectors mapping method
		final Map<Integer, List<String>> collect8 = Stream.of(student, student2)
				.collect(Collectors.groupingBy(m, //map obj
						Collectors.mapping(i -> i.getName(), Collectors.toList())));//collectors interface obj
		System.out.println("collect8 = " + collect8);
//        {25=[hari], 75=[chandan]}

		//m1 adv #1:  in different use case : goal is to convert key to some other type : https://www.baeldung.com/java-groupingby-collector
        final Map<ResponseDto, List<Student>> collect9 = Stream.of(student, student2)
                                    .collect(Collectors.groupingBy(i -> new ResponseDto(i.getName())));//modifying key
        System.out.println("collect9 = " + collect9);
//        {ResponseDto{name='hari'}=[Student{rollNumber=25, name='hari'}], ResponseDto{name='chandan'}=[Student{rollNumber=75, name='chandan'}]}


        //m1 adv #2: key 1, key 2 , values : 3 level mapping or gropping
        final Map<StudentType, Map<Integer, List<Student>>> collect10 = Stream.of(student, student2, student3)
                .collect(Collectors.groupingBy( //this grouping by take 1 argument(see above one), 2 arguments also see here
                        i -> i.getStudentType(), //key 1
                        Collectors.groupingBy(i -> i.getRollNumber())) // key 2 , and values will be default Collectors interface list
                );
        System.out.println("collect10 = " + collect10);
        //output
//        {mtech={25=[Student{rollNumber=25, name='hari'}]},
//        btech={25=[Student{rollNumber=25, name='hari'}], 75=[Student{rollNumber=75, name='chandan'}]}}


        //key type, value avg of roll num (object getter) - find avg using collectors interface method
        final Map<StudentType, Double> collect11 = Stream.of(student, student2, student3)
                .collect(Collectors.groupingBy(i -> i.getStudentType(),
                        Collectors.averagingInt(i -> i.getRollNumber())));//since arg is collectors
                                    // so we can add other collectors interface methods also, we can play with other variation
        System.out.println("collect11 = " + collect11);
//        {mtech=25.0, btech=50.0}

        final Map<StudentType, Long> collect12 = Stream.of(student, student2, student3)
                .collect(Collectors.groupingBy(i -> i.getStudentType(),
                        Collectors.counting()));//imp : in each type what is count of elements
        System.out.println("collect12 = " + collect12);
//        {mtech=1, btech=2}

        //key type of student, value sum of roll num - using collectors interface method reducing, this is similar to reduce method ,
        // reduce method is not part of collectors interface
        final Map<StudentType, Integer> collect13 = Stream.of(student, student2, student3)
                .collect(Collectors.groupingBy(
                        i -> i.getStudentType(),
                        Collectors.reducing(0, a -> a.getRollNumber(), (a, b) -> a + b))
                );
        System.out.println("collect13 = " + collect13);
//        {mtech=25, btech=100}

        //key student type, value names of students : https://stackoverflow.com/questions/56425800/java-collectors-reducing-implementation
        final Map<StudentType, String> collect14 = Stream.of(student, student2, student3)
                .collect(Collectors.groupingBy(
                        i -> i.getStudentType(),
                        Collectors.reducing("", a -> a.getName(), (a, b) -> a + b))
                );
        System.out.println("collect14 = " + collect14);
//        {mtech=hari, btech=chandanhari}

        //key type, value as max roll number in that type, this pattern is useful when we want max like in a particular type of entity
        // logic know different types of Collectors interface methods
        final Map<StudentType, Optional<Student>> collect15 = Stream.of(student, student2, student3)
                .collect(Collectors.groupingBy(
                        i -> i.getStudentType(),
                        Collectors.maxBy(Comparator.comparing(Student::getRollNumber))));
        System.out.println("collect15 = " + collect15);
//        {mtech=Optional[Student{rollNumber=25, name='hari'}], btech=Optional[Student{rollNumber=75, name='chandan'}]}

        //key type, value new obj and in list, my own impl just exploring different method of collectors interface
        final Map<StudentType, List<ResponseDto>> collect16 = Stream.of(student, student2, student3)
                .collect(Collectors.groupingBy(
                        i -> i.getStudentType(),
                        Collectors.mapping(i -> new ResponseDto(i.getName()), Collectors.toList())
                ));
        System.out.println("collect16 = " + collect16);
//        {mtech=[ResponseDto{name='hari'}], btech=[ResponseDto{name='chandan'}, ResponseDto{name='hari'}]}

        //key type, value roll number min in each type
        final Map<StudentType, Optional<Student>> collect17 = Stream.of(student, student2, student3)
                .collect(Collectors.groupingBy(
                        i -> i.getStudentType(),
                        Collectors.minBy(Comparator.comparing(i -> i.getRollNumber()))
                ));
        System.out.println("collect17 = " + collect17);
//        {mtech=Optional[Student{rollNumber=25, name='hari'}], btech=Optional[Student{rollNumber=25, name='hari'}]}

        // break group into TRUE or FALSE based on condition and value will be default as like Collectors interface
        final Map<Boolean, List<Student>> collect18 = Stream.of(student, student2, student3)
                .collect(Collectors.partitioningBy(i -> i.getStudentType().equals(StudentType.btech)));
        System.out.println("collect18 = " + collect18);
        //ouput
//        {false=[Student{rollNumber=25, name='hari'}], true=[Student{rollNumber=75, name='chandan'}, Student{rollNumber=25, name='hari'}]}


    }
}


class ResponseDto {
	String name;

    public ResponseDto(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ResponseDto{" +
                "name='" + name + '\'' +
                '}';
    }
}
