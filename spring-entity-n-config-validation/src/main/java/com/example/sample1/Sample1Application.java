package com.example.sample1;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.sample1.configs.MyConfigs;
import com.example.sample1.entity.Person;
import com.example.sample1.entity.Person2;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class Sample1Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Sample1Application.class, args);
	}

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	MyConfigs myConfigs;

	@Autowired
	MyInterfaceImpl myInterface;

	@Override
	public void run(String... args) throws Exception {

		final Person person = Person.builder().name("hariom").age(30).bigDecimal(BigDecimal.valueOf(999.999)).build();
		System.out.println("person = " + person);
//		foo(person);

		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		final Set<ConstraintViolation<Person>> validate = validator.validate(person);
		validate.forEach(v -> {
			System.out.println("v.getRootBean() = " + v.getRootBean());
			System.out.println("v.getMessage() = " + v.getMessage());
		});

		//1st round off
		BigDecimal inputDecimal = new BigDecimal("999.5000");
		System.out.println("inputDecimal = " + inputDecimal);
		MathContext mathContext = new MathContext(3);//total digit length
		final BigDecimal afterRound = inputDecimal.round(mathContext);
		System.out.println("afterRound = " + afterRound);
		//2nd set zeros after dot
		final BigDecimal afterScale = afterRound.setScale(2);
		System.out.println("afterScale = " + afterScale);

		final Person2 person2 = objectMapper.convertValue(person, Person2.class);
		System.out.println("person2 = " + person2);

		System.out.println("person2 = " + personToPerson2(person));

		//big decimal - maths operations
		BigDecimal decimal = new BigDecimal("120.123");
		System.out.println("decimal = " + decimal);
		BigDecimal decimal2 = new BigDecimal("123.000");
		BigDecimal decimal3 = new BigDecimal("000.001");
		BigDecimal decimal4 = new BigDecimal("2.000");
		System.out.println("subtract = " + decimal.subtract(decimal2));
		System.out.println("add = " + decimal.add(decimal3));
		System.out.println("multiply = " + decimal.multiply(decimal4));
		System.out.println("multiply = " + decimal.multiply(BigDecimal.valueOf(2)));
		System.out.println("divide = " + decimal.divide(BigDecimal.valueOf(2)));
		System.out.println("divide = " + decimal.divide(BigDecimal.valueOf(2)).setScale(4));//scale value depend on previous output if scale value is less than previous integral value then error so use roundoff before set scale
		System.out.println("divide = " + decimal.divide(BigDecimal.valueOf(2)).round(mathContext).setScale(2));//scale value depend on previous output if scale value is less than previous integral value then error so use roundoff before set scale

		System.out.println("myConfigs = " + myConfigs);
		final MyConfigs.MyClass aDefault = myConfigs.getAllUsers().get("default");
		final Integer decimalTest = aDefault.getDecimalTest();
		final String name = aDefault.getName();
		final List<String> visited = aDefault.getVisited();
		System.out.println("aDefault = " + aDefault);
		System.out.println("user-1 = " + myConfigs.getAllUsers().get("user-1"));
		final MyConfigs.MyClass myClass = myConfigs.getAllUsers().get("default");
		final List<BigInteger> phoneNumber = myClass.getPhoneNumber();
		System.out.println("phoneNumber in bigInt = " + phoneNumber);
		final List<String> stringPhoneNumber = phoneNumber.stream().map(i -> String.valueOf(i)).collect(Collectors.toList());
		System.out.println("PhoneNumber in string= " + stringPhoneNumber);

		final Set<String> newConfig = myConfigs.getNewConfig();
		System.out.println("newConfig = " + newConfig);

		final Integer newConfig2 = myConfigs.getNewConfig2();
		System.out.println("newConfig2 = " + newConfig2);

		//interface

		myInterface.defaultMethod();
		myInterface.foo1();
		myInterface.foo2();

		System.out.println("hariom = " + myInterface.defaultMethod2("hariom"));
		System.out.println("round off + set scale = " + myInterface.defaultMethod3(BigDecimal.valueOf(123.123)));
		final BigDecimal negate = BigDecimal.valueOf(123.123).negate();
		System.out.println("negate = " + negate);
		System.out.println(negate.compareTo(BigDecimal.valueOf(0)));

		final BigDecimal decimal1 = MyInterface.staticMethod(BigDecimal.valueOf(123.123));
		System.out.println("via static method = " + decimal1);

	}

	public void foo(@Valid Person person) {
		System.out.println("person = " + person);
	}



	@Autowired
	ModelMapper modelMapper;

	public Person2 personToPerson2(Person person) {
		final Person2 person2 = modelMapper.map(person, Person2.class);//this is same as objectmapper
//		person2.setName("hari");//this is required when both entity have different name or different type
//		person2.setBigDecimal(person.getBigDecimal());
		return person2;
	}
}
