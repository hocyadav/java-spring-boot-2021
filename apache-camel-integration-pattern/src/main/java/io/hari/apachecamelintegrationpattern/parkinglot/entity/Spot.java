package io.hari.apachecamelintegrationpattern.parkinglot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Spot {
    Integer id;
    String status;
}
