package io.hari.demo;

import io.hari.demo.dao.UserDao;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Author Hariom Yadav
 * @create 20-04-2021
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest //https://reflectoring.io/spring-boot-data-jpa-test/
//    https://www.baeldung.com/spring-boot-testing
    // https://zetcode.com/springboot/datajpatest/#:~:text=%40DataJpaTest%20is%20used%20to%20test,configuration%20relevant%20to%20JPA%20tests.
class UserDaoTest {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private UserDao userRepository;

    @Test
    void injectedComponentsAreNotNull() {
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(userRepository).isNotNull();
    }
}
