package com.hari.jpa1;

import lombok.SneakyThrows;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class JavaStreamAllMethodsImpl implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(JavaStreamAllMethodsImpl.class, args);
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

        //int arr to Integer Stream or Integer Array
        final Stream<Integer> boxed = Arrays.stream(arr).boxed();
        final List<Integer> integerList = Arrays.stream(arr).boxed().collect(Collectors.toList());

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

        //above grop by collect6 and 7 are same as below
        final Map<Integer, List<Student>> collect33 = Stream.of(student, student2).collect(Collectors.groupingBy(m,
                Collectors.mapping(i -> i, Collectors.toList())//value we can use what we want , by default in above it is calling this same logic
        ));
        System.out.println("collect33 = " + collect33);
        // collect33 = {25=[Student{rollNumber=25, name='hari'}], 75=[Student{rollNumber=75, name='chandan'}]}



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
                        Collectors.reducing(0, //initial/default value for below selected field
                                a -> a.getRollNumber(), //select field that we want to perform binaryOperator/accumulator
                                (a, b) -> a + b))//accumulator/binary oprator operation or static method
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


        //reduce and reducing : reduce means "reduction operation"
        final Entity name1 = Entity.builder().name("name1").entityType(Entity_type.type1).id(1).priceBigDecimal(BigDecimal.valueOf(100)).list(Arrays.asList("str 1", "str 2")).build();
        final Entity name2 = Entity.builder().name("name2").entityType(Entity_type.type2).id(2).priceBigDecimal(BigDecimal.valueOf(200)).list(Arrays.asList("str 3", "str 4")).build();
        final Entity name3 = Entity.builder().name("name3").entityType(Entity_type.type3).id(3).priceBigDecimal(BigDecimal.valueOf(300)).list(Arrays.asList("str 4", "str 5")).build();
        final Entity name4 = Entity.builder().name("name4").entityType(Entity_type.type3).id(4).priceBigDecimal(BigDecimal.valueOf(200)).list(Arrays.asList("str 4", "str 5")).build();

        final int identity = 10;//initial value / default value of "reduction operation", return default value when stream is empty
        final BinaryOperator<Integer> accumulator = (a, b) -> a + b; //partial result + next element in stream
//        final BinaryOperator<Integer> accumulator2 = (partialResult, nextElement) -> partialResult + nextElement; //partial result + next element in stream
        final Integer reduce3 = Stream.of(name1, name2, name3).map(i -> i.getId())
                                        .reduce(identity, accumulator);//T reduce(T identity, BinaryOperator<T> accumulator);
        System.out.println("reduce3 = " + reduce3);

        final BinaryOperator<Integer> accumulator1 = Integer::sum;
        final Integer reduce4 = Stream.of(name1, name2, name3).map(i -> i.getId()).reduce(identity, accumulator1);//same as above
        System.out.println("reduce4 = " + reduce4);
        // sum internal impl its a static method that takes 2 arg and sum that, 1st arg is like partialResult and next is like nextElement in stream
//        public static int sum(int a, int b) {
//            return a + b;
//        }

        final Optional<Integer> reduce5 = Stream.of(name1, name2, name3).map(i -> i.getId()).reduce(accumulator);//simply adding all stream data
        System.out.println("reduce5 = " + reduce5.get());

        BinaryOperator<BigDecimal> accumulator3 = new BinaryOperator<BigDecimal>() {
            @Override
            public BigDecimal apply(BigDecimal decimal, BigDecimal decimal2) {
                return decimal.add(decimal2);
            }
        };
        final Optional<BigDecimal> reduce6 = Stream.of(name1, name2, name3).map(i -> i.getPriceBigDecimal())
                                                    .filter(Objects::nonNull)//if we not add this one then throw error, since we dont know which obj has this field value and some may have null
                                                    .reduce(accumulator3);//or we can pass static method that will take inpu Bigdecimal
        System.out.println("reduce6 = " + reduce6);

        final BigDecimal deliveryCharge = BigDecimal.valueOf(10);
        final BigDecimal reduce7 = Stream.of(name1, name2, name3).map(i -> i.getPriceBigDecimal())
                .filter(Objects::nonNull)
                .reduce(deliveryCharge, ResponseDto::staticMethodSumAndAddDiscount);
        System.out.println("reduce7 = " + reduce7);

        final BigDecimal discount = BigDecimal.valueOf(50).negate();
        final BigDecimal reduce8 = Stream.of(name1, name2, name3).map(i -> i.getPriceBigDecimal()).filter(Objects::nonNull)
                .reduce(discount, ResponseDto::staticMethodSumAndAddDiscount);
        System.out.println("reduce8 = " + reduce8);

        final BigDecimal reduce9 = Stream.of(name1, name2, name3).map(i -> i.getPriceBigDecimal()).filter(Objects::nonNull)
                .reduce(discount.add(deliveryCharge), ResponseDto::staticMethodSumAndAddDiscount);
        System.out.println("reduce9 = " + reduce9);

        //TODO : 3 below find sum of field give same output
        final BinaryOperator<BigDecimal> sumAccumulator = BigDecimal::add;
        final Optional<BigDecimal> allSum_AllPrice = Stream.of(name1, name2, name3)
                .map(i -> i.getPriceBigDecimal()).filter(Objects::nonNull)//always use map + filter non null
                .reduce(sumAccumulator);//output is optional since there is not intial default value
        System.out.println("allSum_AllPrice = " + allSum_AllPrice);

        final BigDecimal AllSum_AllPrice2 = Stream.of(name1, name2, name3)
                .map(i -> i.getPriceBigDecimal()).filter(Objects::nonNull)//always use map + filter non null
                .reduce(BigDecimal.ZERO, sumAccumulator);// initial value so there is no optional in return in worst case it will return 0 is no stream
        System.out.println("AllSum_AllPrice2 = " + AllSum_AllPrice2);

        final BigDecimal collect19 = Stream.of(name1, name2, name3)
                .map(i -> i.getPriceBigDecimal()).filter(Objects::nonNull)//always use map + filter non null
                .collect(Collectors.reducing(BigDecimal.ZERO, sumAccumulator));
        System.out.println("collect19 = " + collect19);

        System.out.println("original price or sum : " + allSum_AllPrice + " or " + AllSum_AllPrice2 + " or " + collect19);
        System.out.println("price or sum after discount = " + reduce9);

        //use case : based on type i want price sum
        final Map<Entity_type, List<Entity>> collect20 = Stream.of(name1, name2, name3, name4)
                .collect(Collectors.groupingBy( //internally it will call groupingBy(classifier, toList());
                            i -> i.getEntityType()
                     ));
        System.out.println("collect20 = " + collect20);

        final BinaryOperator<String> operatorOrAccumulator = (a, b) -> a + b;
        final Map<Entity_type, String> collect21 = Stream.of(name1, name2, name3, name4)
                .collect(Collectors.groupingBy(
                        i -> i.getEntityType(),
                        Collectors.reducing("",//initial value or default value
                                a -> a.getName(), // in stream obj which getter field we want to add
                                operatorOrAccumulator) // for above selected getter what operation we want to do
                ));
        System.out.println("collect21 = " + collect21);

        final BigDecimal identity1 = BigDecimal.ZERO;
        final BinaryOperator<BigDecimal> binaryOperatorOrAccumulator = BigDecimal::add;
        final Map<Entity_type, BigDecimal> collect22 = Stream.of(name1, name2, name3, name4)
                .collect(Collectors.groupingBy(
                        i -> i.getEntityType(),
                        Collectors.reducing(
                                identity1,
                                a -> a.getPriceBigDecimal(),//select field type for accumulator
                                binaryOperatorOrAccumulator //this accumulator is for above field type
                        )
                ));
        System.out.println("collect22 = " + collect22);


        //reducing all 3 types : all will give same output
        //m1
//        name1.setPriceBigDecimal(null);//working for null field coz we have added filter object non null
        final Optional<BigDecimal> collect23 = Stream.of(name1, name2, name3, name4).map(i -> i.getPriceBigDecimal()).filter(Objects::nonNull)
                .collect(Collectors.reducing(binaryOperatorOrAccumulator));//optional coz no initial value
        System.out.println("collect23 = " + collect23);
        //m2
//        name1.setPriceBigDecimal(null);
//        name2.setPriceBigDecimal(null);
//        name3.setPriceBigDecimal(null);
//        name4.setPriceBigDecimal(null);//working
        final BigDecimal collect24 = Stream.of(name1, name2, name3, name4).map(Entity::getPriceBigDecimal).filter(Objects::nonNull)
                .collect(Collectors.reducing(BigDecimal.ZERO, binaryOperatorOrAccumulator));// extension of above, adding default value for empty stream
        System.out.println("collect24 = " + collect24);
        //m3 : in all above we have perform map before calling collect reducing , here we can call inside reducing method
//        name1.setPriceBigDecimal(null);
//        name2.setPriceBigDecimal(null);
//        name3.setPriceBigDecimal(null);
//        name4.setPriceBigDecimal(null);
        //if obje field(price field) is null then below mapper will break since no object null check
        //todo : not work if any field price contain null, so if above code testing for null then comment this stream
        final BigDecimal collect25 = Stream.of(name1, name2, name3, name4)
                .collect(Collectors.reducing(BigDecimal.ZERO, i -> i.getPriceBigDecimal(), BigDecimal::add));
        System.out.println("collect25 = " + collect25);

        // <R> Stream<R> map(Function<? super T, ? extends R> mapper); // this is from internal  map() method
         //        Function<? super T, ? extends U> mapper,// this is internal from reducing argument
        //we can see both are taking same type argument

        // filter non null working
        name1.setPriceBigDecimal(null);
        name2.setPriceBigDecimal(null);
        name3.setPriceBigDecimal(null);
        name4.setPriceBigDecimal(null);
        final List<BigDecimal> collect26 = Stream.of(name1, name2, name3, name4).map(Entity::getPriceBigDecimal).filter(Objects::nonNull)
                .collect(Collectors.toList());

        System.out.println("collect26 = " + collect26);

        final BigDecimal collect27 = Stream.of(name1, name2, name3, name4).map(Entity::getPriceBigDecimal).filter(Objects::nonNull)
                .collect(Collectors.reducing(BigDecimal.ZERO, BigDecimal::add));
        System.out.println("collect27 = " + collect27);




        //todo https://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/
        //DONE : stream from supplier
        //anyMatch, all, none
        //best practice order : flatmap, sorted, map, foreach
        //DONE : parallel stream : list.parallelStream().forEach(element -> doWork(element));
        //count stream: long count = list.stream().distinct().count();
        //DONE : stream to stream , flatmap : details.stream().flatMap(detail -> detail.getParts().stream());
        //DONE : stream averaging and summarizing
         List<Detail> details = Arrays.asList(
                 Detail.builder().name("hari").parts(Arrays.asList(Part.builder().name("part 1").build())).age(30).build(),
                 Detail.builder().name("om").parts(Arrays.asList(Part.builder().name("part 2").build())).age(20).build(),
                 Detail.builder().name("yadav").parts(Arrays.asList(Part.builder().name("part 3").build())).age(10).build(),
                 Detail.builder().name("omprakash").parts(Arrays.asList(Part.builder().name("part 4").build())).age(10).build());
        final Stream<Part> partStream = details.parallelStream().filter(Objects::nonNull).flatMap(i -> i.getParts().stream());
        System.out.println("partStream = " + partStream);

        //problem if we use stream multiple times then IllegalStateException change error, create a supplier for stream
        final Supplier<Stream<Part>> streamSupplier = () -> partStream;

        Stream<String> stringStream1 =
                Stream.of("d2", "a2", "b1", "b3", "c")
                        .filter(s -> s.startsWith("a"));
        stringStream1.anyMatch(s -> true);    // ok
//        stringStream1.noneMatch(s -> true);   // exception

        final Stream<Part> partStream1 = streamSupplier.get();
        final Stream<Part> partStream2 = streamSupplier.get();

        final Supplier<Stream<Detail>> supplierDetailStream = () -> details.parallelStream();
        final Double averageAge = supplierDetailStream.get()
                .collect(Collectors.averagingInt(i -> i.getAge()));
        System.out.println("averageAge = " + averageAge);
        final IntSummaryStatistics statistics = supplierDetailStream.get().collect(Collectors.summarizingInt(i -> i.getAge()));
        System.out.println("statistics = " + statistics);
        System.out.println("statistics.getSum() = " + statistics.getSum());
        System.out.println("statistics.getMax() = " + statistics.getMax());
        System.out.println("statistics.getMin() = " + statistics.getMin());
        System.out.println("statistics.getAverage() = " + statistics.getAverage());

//        final Map<Integer, String> collect29 = supplierDetailStream.get()
//                .collect(Collectors.toMap(k -> k.getAge(), v -> v.getName()));//this will work fine when keys are unique
//        System.out.println("collect29 = " + collect29);

        final Map<Integer, String> collect28 = supplierDetailStream.get()
                .collect(Collectors.toMap(k -> k.getAge(), v -> v.getName(),
                        (a, b) -> a +","+ b));//this binary operation /accumulaor is applied on above value type
        //3rd argument is optional use when keys are not unique, this is only when same key can have 2 value then
                                            // state exception to avoid that we need to add how to merge those value into one
        System.out.println("collect28 = " + collect28);


        //awesome
        final HashMap<String, String> collect29 = supplierDetailStream.get().collect(Collectors.toMap(
                k -> k.getName(), //mapper
                v -> v.getName(), //mapper
                (a, b) -> a + ";" + b, //accumulator for value mapper, i.e. for 2nd argument
                () -> new HashMap<>()));//this is for what type of map u want, hashmap or concurrentMap etc
        System.out.println("collect29 = " + collect29);

        final ConcurrentHashMap<Integer, String> collect31 = supplierDetailStream.get().collect(Collectors.toMap(
                k -> k.getAge(),
                v -> v.getName(),
                (a, b) -> a + "," + b,
                () -> new ConcurrentHashMap<>()//what type of map u want in output
        ));
        System.out.println("collect31 = " + collect31);

        final TreeMap<Integer, String> collect32 = supplierDetailStream.get().collect(Collectors.toMap(
                k -> k.getAge(),
                v -> v.getName(),
                (a, b) -> a + "-" + b,
                () -> new TreeMap<>()//sorted order on key
        ));
        System.out.println("collect32 = " + collect32);

        List<Detail> details2 = null;
        //if we are not sure collection list is null then always use below line to create a empty collection if it is null
        final List<Detail> details1 = Optional.ofNullable(details2).orElse(Collections.emptyList());//IMP : null check for above collection
        final Map<Integer, String> collect30 = details1.stream().filter(Objects::nonNull).collect(Collectors.toMap(
                k -> k.getAge(),
                v -> v.getName(),
                (a, b) -> b + "," + b));//if keys are not same
        System.out.println("collect30 = " + collect30);

        //todo own collectors
//        Collector< Detail, StringJoiner, String> ownCollector = Collector.of(
//                () -> new StringJoiner(" | "),
//                (a, b) -> a + " " + b,
//                (c, d) -> c.merge(d),
//                i -> i.toString()
//        );
//
//        supplierDetailStream.get().collect(ownCollector);


        //TODO DONE : stream with Checked exceptions
        final List<String> list1 = Arrays.asList("file1.txt", "file2.txt");
        final List<List<String>> collect34 = list1.stream()
                .map(file -> Paths.get(file))
                .map(path -> {
                    try {
                        return Files.readAllLines(path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                }).collect(Collectors.toList());
        System.out.println("collect34 = " + collect34);

        final List<List<String>> collect35 = list1.stream()
                .map(file -> Paths.get(file))
                .map(path -> this.readAllLines(path))
                .collect(Collectors.toList());
        System.out.println("collect35 = " + collect35);

        final List<List<String>> collect36 = list1.stream().map(file -> Paths.get(file))
                .map(path -> this.readLinesFrom2(path))
                .collect(Collectors.toList());
        System.out.println("collect36 = " + collect36);

        final List<List<String>> collect37 = list1.stream()
                .map(f -> Paths.get(f))
                .map(p -> this.readLinesFrom3(p))
                .collect(Collectors.toList());
        System.out.println("collect37 = " + collect37);


    }

    private List<String> readAllLines(Path path) {
        List<String> strings = new LinkedList<>();
        try {
            strings = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strings;
    }

    //wrap unchecked exception to run time exception
    public List<String> readLinesFrom3(Path path) {//convert unchecked exceptions to runtime exceptions
        List<String> strings = new ArrayList<>();
        try {
            strings = Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return strings;
    }


    @SneakyThrows
    public List<String> readLinesFrom2(Path path) {//throw unchecked exception using Lombok
        return Files.readAllLines(path);
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

    public static BigDecimal staticMethodSumAndAddDiscount(BigDecimal value1, BigDecimal value2) {
        return value1.add(value2);
    }
}
