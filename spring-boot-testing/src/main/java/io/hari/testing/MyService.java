package io.hari.testing;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class MyService {

    public String calculateTax(int data) {
        if (data < 500) {
            return "LOW";
        } else if (data >= 500 && data < 1000) {
            return "MEDIAM";
        } else {
            return "HIGH";
        }
    }

    public List<String> allTaxBracket() {
        return Arrays.asList("LOW", "MEDIAM", "HIGH");
    }
}
