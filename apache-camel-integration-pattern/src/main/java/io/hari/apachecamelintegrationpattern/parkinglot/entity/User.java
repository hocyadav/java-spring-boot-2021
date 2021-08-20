package io.hari.apachecamelintegrationpattern.parkinglot.entity;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class User {
    Integer id;
    String vehicleDetail;
}
