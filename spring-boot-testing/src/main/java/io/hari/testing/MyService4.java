package io.hari.testing;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Setter
@Service
public class MyService4 {

    @Autowired
    NetwokCallOrDBCall_InOtherService netwokCallOrDBCallService;

    public BigDecimal calculateTax(BigDecimal data) {
        final BigDecimal otherService = netwokCallOrDBCallService.timeTakingOperationInOtherService(data);
        final BigDecimal multiply = otherService.multiply(BigDecimal.valueOf(2));
        logForAudit();
        return multiply;
    }

    public void logForAudit() {
        //network call
        System.out.println("log -- netwokCallOrDBCallService = " + netwokCallOrDBCallService);
    }
}
