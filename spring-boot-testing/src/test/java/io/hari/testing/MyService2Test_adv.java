package io.hari.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest // all beans loaded -> then below autowire takes place,
// if too many beans then it will take more time to initialize , so use contextCOnfiguration
class MyService2Test_adv {

    @Autowired
    MyService2 myService2;

    @Autowired
    TaxBracketService taxBracketService;

    @BeforeEach
    void setUp() {
        //not required constructor call
    }

    @Test
    void calculateTax() {
        final String tax = myService2.calculateTax(499);
        assertThat(tax).isEqualTo("LOW");
    }

    @Test
    void allTaxBracket() {
        final List<String> allTaxBracket = myService2.allTaxBracket();
        assertThat(allTaxBracket)
                .contains("LOW", "MEDIAM", "HIGH")
                .doesNotContain("XYZ");
    }
}