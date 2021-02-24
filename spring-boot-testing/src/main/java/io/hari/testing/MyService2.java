package io.hari.testing;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Setter
@Service
public class MyService2 {

    @Autowired
    TaxBracketService taxBracketService;

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
        return taxBracketService.all();
    }
}
