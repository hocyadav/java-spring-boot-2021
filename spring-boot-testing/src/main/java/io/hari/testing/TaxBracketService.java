package io.hari.testing;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TaxBracketService {
    public List<String> all() {
        return Arrays.asList("LOW", "MEDIAM", "HIGH");
    }
}
