package io.hari.demo.entity;

import com.sun.javafx.css.parser.LadderConverter;
import io.hari.demo.convertor.LocationConverter;
import io.hari.demo.convertor.TripStatus;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author Hariom Yadav
 * @create 02-04-2021
 */
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {}, callSuper = true)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "trips")
public class Trip extends BaseEntity{
    Long userId;
    Long cabId;
    BigDecimal price;

    @Convert(converter = LocationConverter.class)
    Location fromLocation;

    @Convert(converter = LocationConverter.class)
    Location toLocation;

    LocalDateTime endTripTime;

    @Enumerated(EnumType.STRING)
    TripStatus tripStatus;
}
