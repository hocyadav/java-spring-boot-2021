package io.hari.testing;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class NetwokCallOrDBCall_InOtherService {

    public BigDecimal timeTakingOperationInOtherService(BigDecimal data) {
        System.err.println("NetwokCallOrDBCall_InOtherService.timeTakingOperationInOtherService");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return data.add(BigDecimal.valueOf(100));
    }
}
