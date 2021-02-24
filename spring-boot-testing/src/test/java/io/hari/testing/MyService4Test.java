package io.hari.testing;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MyService4.class})
class MyService4Test {

//    @Autowired
    @SpyBean//coz we are ignoring one method inside service class
    MyService4 myService4;

    @MockBean //coz we are ignoring all method call
    NetwokCallOrDBCall_InOtherService netwokCallOrDBCall_inOtherService;

    @Test
    void calculateTax() {
        final BigDecimal data = new BigDecimal(100);
        Mockito.when(netwokCallOrDBCall_inOtherService.timeTakingOperationInOtherService(data))
                .thenReturn(new BigDecimal(200));
        //spy bean
        Mockito.doNothing().when(myService4).logForAudit();

        final BigDecimal tax = myService4.calculateTax(data);//actual service 3rd party all
        assertThat(tax)
                .isNotNull()
                .isEqualTo(new BigDecimal(400));
    }
}