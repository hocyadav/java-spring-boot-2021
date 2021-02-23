package io.hari.att;

import io.hari.att.config.MyConfig;
import io.hari.att.convertor.CryptoConverter;
import io.hari.att.dao.PersonDao;
import io.hari.att.entity.Address;
import io.hari.att.entity.AgeType;
import io.hari.att.entity.Person;
import io.hari.att.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SpringBootApplication
public class AttributeConvertorJsonStoreGenericDaoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AttributeConvertorJsonStoreGenericDaoServiceApplication.class, args);
    }
    @Autowired
    PersonDao personDao;

    @Autowired
    MyConfig myConfig;

    @Autowired
    PersonService personService;

    @Autowired
    CryptoConverter cryptoConverter;

    @PostConstruct
    public void foo() {
        final String ccNumber = "495452 560037 802220 495452";
        System.out.println("ccNumber = " + ccNumber);
        final String cardNumberInDb = cryptoConverter.convertToDatabaseColumn(ccNumber);
        System.err.println("cardNumberInDb = " + cardNumberInDb);
        final String actualCardNum = cryptoConverter.convertToEntityAttribute(cardNumberInDb);
        System.err.println("actualCardNum = " + actualCardNum);

        final String fieldNameSimple = myConfig.getFieldNameSimple();
        System.out.println("fieldNameSimple = " + fieldNameSimple);

        final Map<String, String> fieldName = myConfig.getFieldName();
        System.out.println("fieldName = " + fieldName);

        final Map<String, MyConfig.ClassA> fieldNameObject = myConfig.getFieldNameObject();
        System.out.println("fieldNameObject = " + fieldNameObject);
        System.out.println("fieldNameObject keyAd = " + fieldNameObject.get("keyAd"));

        final Map<String, MyConfig.ClassB> fieldNameAdObject = myConfig.getFieldNameAdObject();
        System.out.println("fieldNameAdObject = " + fieldNameAdObject);
        final MyConfig.ClassB keyA = fieldNameAdObject.get("keyA");
        final String name = keyA.getName();
        System.out.println("name = " + name);
        final Set<String> places = keyA.getPlaces();
        System.out.println("places = " + places);
        final Map<String, String> keyValues = keyA.getKeyValues();
        System.out.println("keyValues = " + keyValues);

        personDao.save(Person.builder().name("hari").ageType(AgeType.GENERATION2)
                .dob(LocalDate.of(1989, Month.JUNE, 26))
                .creditCardNumber("495452 560037 802220")
                .address(Address.builder().pincode("495452").build()).build());

        personDao.save(Person.builder().name("hariom yadav").ageType(AgeType.GENERATION1)
                .dob(LocalDate.of(1989, Month.JUNE, 26))
                .creditCardNumber("495452 560037 802220")
                .address(Address.builder().pincode("495452").build()).build());

        final List<Person> people = personDao.findAll();
        people.forEach(i -> {
            System.out.println("i = " + i);
        });

        final Person person = people.get(0);
        System.out.println("person = " + person);
        final Address address = person.getAddress();
        System.out.println("address = " + address);

        final List<Person> allByAgeType = personDao.findAllByAgeType(AgeType.GENERATION1);
        System.out.println("allByAgeType = " + allByAgeType);

        final List<Person> personList = personService.findAll();
        System.err.println("personList = " + personList);
    }
}
