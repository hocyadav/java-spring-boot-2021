package io.hari.testing;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class MyService3Test {

    @Autowired
    MyService3 service3;

    @Autowired
    NetwokCallOrDBCall_InOtherService netwokCallOrDBCall_inOtherService;//autowire coz service3 internally calling

    @Test
    void calculateTax() {
        final BigDecimal data = BigDecimal.valueOf(100);
        final BigDecimal tax = service3.calculateTax(data);//actual service 3rd party all
        assertThat(tax)
                .isNotNull()
                .isEqualTo(new BigDecimal(400));
    }
}