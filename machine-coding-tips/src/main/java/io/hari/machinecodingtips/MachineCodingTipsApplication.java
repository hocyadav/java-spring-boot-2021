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
//		validationTest();
		modelMapperTest();
	}

	private void modelMapperTest() {
		myService.foo();
	}

	private void validationTest() {
		EntityA hariom = EntityA.builder()
				.name("")
				.email("hari@gmaul.com")
//				.phoneNumber(BigInteger.valueOf(9887700499L))
				.dateOfBirth(LocalDate.of(1989, 06, 26)).build();
		System.out.println("hariom = " + hariom);

		//old impl
		myServiceOldStyle.stringMethod(hariom);//validate all Entity annotation, that have no group mentioned

		//new impl
		myServiceNewStyle.stringMethod(hariom);//only string group validation
		myServiceNewStyle.numberMethod(hariom);//only number group validation
	}
}
