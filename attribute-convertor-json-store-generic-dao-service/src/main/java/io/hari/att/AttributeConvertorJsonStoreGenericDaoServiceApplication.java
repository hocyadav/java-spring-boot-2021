package io.hari.att;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.hari.att.config.MyConfig;
import io.hari.att.convertor.CryptoConverter;
import io.hari.att.dao.PersonDao;
import io.hari.att.entity.*;
import io.hari.att.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

@SpringBootApplication
@EnableScheduling
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

    @Scheduled(fixedDelay = 1000)//1000 = 1 sec
    public void scheduledFun() {
        System.err.println("QuartzApplication.foo");
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");

        Map<AttributeKey, String> map2 = new HashMap<>();
        map2.put(AttributeKey.cc_num, "495452 560037");
        map2.put(AttributeKey.phone_num, "9887700499");

        personDao.save(Person.builder().name("hari").ageType(AgeType.GENERATION2)
                .dob(LocalDate.of(1989, Month.JUNE, 26))
                .creditCardNumber("495452 560037 802220")
                .jsonEntity(JsonEntity.builder().name("json name").rollNum("1234")
                        .stringMap(map)
                        .build())
                .attribute(EntityAttribute.builder().attributes(map2).build())
                .address(Address.builder().pincode("495452").build()).build());
    }

    @PostConstruct
    public void foo() {
        //native query test
        final Person people1 = personDao.myOwnQueryNative("hari");
        System.err.println("myOwnQueryNative people1 = " + people1);

        final List<Person> people2 = personDao.finadAllByQuery();
        System.err.println(" finadAllByQuery people2 = " + people2);

        //not implemented
        final List<Person> foo = personDao.createOwnMethodWithSQL();
        System.err.println("foo = " + foo);


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

        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");

        personDao.save(Person.builder().name("hari").ageType(AgeType.GENERATION2)
                .dob(LocalDate.of(1989, Month.JUNE, 26))
                .creditCardNumber("495452 560037 802220")
                .jsonEntity(JsonEntity.builder().name("json name").rollNum("1234")
                        .stringMap(map)
                        .build())
                .address(Address.builder().pincode("495452").build()).build());

        Map<AttributeKey, String> map2 = new HashMap<>();
        map2.put(AttributeKey.cc_num, "495452 560037");
        map2.put(AttributeKey.phone_num, "9887700499");
        personDao.save(Person.builder().name("hariom yadav").ageType(AgeType.GENERATION1)
                .dob(LocalDate.of(1989, Month.JUNE, 26))
                .creditCardNumber("495452 560037 802220")
                .attribute(EntityAttribute.builder().attributes(map2).build())
                .address(Address.builder().pincode("495452").build()).build());

        ObjectMapper objectMapper = new ObjectMapper();

        final List<Person> people = personDao.findAll();
        people.forEach(people_ -> {
            System.err.println("people_ = " + people_);
            try {
                final String valueAsString = objectMapper.writeValueAsString(people_);//option , use toString intead
                System.err.println("valueAsString = " + valueAsString);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            final JsonEntity jsonEntity = people_.getJsonEntity();
            System.err.println("jsonEntity = " + jsonEntity);
            //get json object form map
            if (jsonEntity != null) {
                final String name1 = jsonEntity.getName();
                System.out.println("name1 = " + name1);
                final String rollNum = jsonEntity.getRollNum();
                System.out.println("rollNum = " + rollNum);
                final Map<String, String> stringMap = jsonEntity.getStringMap();
                System.out.println("stringMap = " + stringMap);
            }
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
