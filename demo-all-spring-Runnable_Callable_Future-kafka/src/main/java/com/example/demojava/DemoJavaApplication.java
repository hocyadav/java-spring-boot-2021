package com.example.demojava;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import com.example.demojava.jpa_set_convertor.dao.PNumDao;
import com.example.demojava.jpa_set_convertor.entity.PhoneNumber;

@SpringBootApplication
public class DemoJavaApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(DemoJavaApplication.class, args);
    }
    @Autowired
    PNumDao pNumDao;

    @Override
    public void run(String... args) throws Exception {
        System.err.println("DemoJavaApplication.run");

        pNumDao.save(getPhoneNumber());
        pNumDao.save(getPhoneNumber());
        pNumDao.save(getPhoneNumber());
        pNumDao.save(getPhoneNumber());
        pNumDao.save(getPhoneNumber());
        pNumDao.save(getPhoneNumber());

        pNumDao.findAll().stream().forEach(System.out::println);
    }

    private PhoneNumber getPhoneNumber() {
        final PhoneNumber phoneNumber = new PhoneNumber();
        List<String> list = new ArrayList<>();
        list.add("9887700499");
        list.add("9887700412");
        phoneNumber.setPhoneNum(list);
        return phoneNumber;
    }
}
