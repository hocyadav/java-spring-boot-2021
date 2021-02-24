package io.hari.testing;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class MyService3Test_mock {

    @Autowired//spring will create actual bean
    MyService3 service3;

    @MockBean// spring will create mock bean
    NetwokCallOrDBCall_InOtherService netwokCallOrDBCall_inOtherService;//autowire coz service3 internally calling

    @Test
    void calculateTax() {
        final BigDecimal data = new BigDecimal(100);
        //added mock for netwok call before actual service call
        Mockito.when(netwokCallOrDBCall_inOtherService.timeTakingOperationInOtherService(data))
                .thenReturn(new BigDecimal(200));

        final BigDecimal tax = service3.calculateTax(data);//actual service 3rd party all
        assertThat(tax)
                .isNotNull()
                .isEqualTo(new BigDecimal(400));
    }
}