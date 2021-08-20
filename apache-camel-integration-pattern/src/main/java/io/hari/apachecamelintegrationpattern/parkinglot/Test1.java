package io.hari.apachecamelintegrationpattern.parkinglot;

import io.hari.apachecamelintegrationpattern.parkinglot.entity.Spot;

public class Test1 {
    public static void main(String[] args) {
        Spot spot = new Spot();
        Spot available = Spot.builder()
                .id(1)
                .status("available")
                .build();
    }
}
