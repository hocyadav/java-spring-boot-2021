package io.hari.testing;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
class MyServiceTest {

    private MyService myService = new MyService();

    @Test
    void calculateTax() {
        final String tax = myService.calculateTax(499);
//        assertEquals(tax, "LOW"); //old style
        assertThat(tax).isEqualTo("LOW");//new way
    }

    @Test
    void allTaxBracket() {
        final List<String> allTaxBracket = myService.allTaxBracket();
        assertNotNull(allTaxBracket);//old style

        assertThat(allTaxBracket).isNotEmpty();//new style assertThat comes from libery assertJ,
        // this is part of sprng boot starter test dependency, it is very powerful
        assertThat(allTaxBracket).contains("LOW", "MEDIAM", "HIGH");
    }

    @Test
    public void foo() {
          assertThat("mic check 1 2 3, mic check")//replace insid string with method call
                  .startsWith("mic check")
                  .endsWith("mic check")
                  .contains("1", "2", "3");
    }

    @Test
    public void fooList() {
        final ArrayList<String> strings = newArrayListOwnIMPL("hariom", "chandan");
        assertThat(strings)
                .doesNotContainNull()
                .containsAnyOf("hariom", "chandan", "omp")
                .doesNotContain("yadav");
    }

    @Test
    public void foo2() {
        final ArrayList<String> objects = null;
        assertThat(objects)
                .isNullOrEmpty();
    }

    public static <T> ArrayList<T> newArrayListOwnIMPL(T... elements) {
        if (elements == null) {
            return null;
        } else {
            ArrayList<T> list = new ArrayList<>();
            Collections.addAll(list, elements);
            return list;
        }
    }
}