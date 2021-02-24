package io.hari.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@RunWith(SpringRunner.class)
class MyService2Test {

    MyService2 myService2;

    @BeforeEach
    void setUp() {//remove this by adding @SpringBootTest + @Autowire both class
        TaxBracketService taxBracketService = new TaxBracketService();
        myService2 = new MyService2();
        myService2.setTaxBracketService(taxBracketService);
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