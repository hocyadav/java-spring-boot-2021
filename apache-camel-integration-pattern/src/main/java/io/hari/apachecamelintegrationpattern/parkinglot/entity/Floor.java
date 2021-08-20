package io.hari.apachecamelintegrationpattern.parkinglot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Floor {
    Integer id;
    String status;
    List<Spot> spots;
}
