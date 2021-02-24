package io.hari.testing;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MyService2.class, TaxBracketService.class})//only load 2 beans
//@ContextConfiguration(classes = {MyService2.class, TaxBracketService.class})// only loading 2 bean : old annotaion not working
class MyService2Test_adv_2 {

    @Autowired
    MyService2 myService2;

    @Autowired
    TaxBracketService taxBracketService;

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