package io.hari.demo;

import io.hari.demo.dao.UserDao;
import io.hari.demo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

/**
 * @Author Hariom Yadav
 * @create 20-04-2021
 */
@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Rollback(value = false)
@RunWith(SpringRunner.class)
//@SpringBootTest
// https://zetcode.com/springboot/datajpatest/#:~:text=%40DataJpaTest%20is%20used%20to%20test,configuration%20relevant%20to%20JPA%20tests.
public class JpaTest {
    @Autowired
    UserDao userDao;

    @Test
    public void createUser() {
        //todo: create 2 users
        User hariom = User.builder().name("hariom 2").build();
        User chandan = User.builder().name("chandan 2").build();
        userDao.saveAll(Arrays.asList(hariom, chandan));
        userDao.findAll().stream().forEach(System.out::println);
    }

}
