package com.hari.jpa1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
		Optional.ofNullable(null).orElseThrow(() -> new RuntimeException("value is null..."));//working
		Optional.ofNullable("value").orElseThrow(() -> new RuntimeException("value is null..."));//working


		final Optional<Integer> integer = Optional.of(123);
		final Optional<Integer> integer2 = Optional.of(123);
		final boolean equals1 = integer.equals(integer);
		System.out.println("equals1 = " + equals1);


	}
}
