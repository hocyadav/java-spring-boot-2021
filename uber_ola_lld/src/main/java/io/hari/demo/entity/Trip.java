package io.hari.demo.entity;

import io.hari.demo.convertor.LocationConverter;
import io.hari.demo.convertor.TripStatus;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Duration;
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
public class Trip extends BaseEntity {
    Long userId;
    Long cabId;
    BigDecimal price;

    @Convert(converter = LocationConverter.class)
    Location fromLocation;

    @Convert(converter = LocationConverter.class)
    Location toLocation;

    //metadata
    LocalDateTime endTripTime;

    @Enumerated(EnumType.STRING)
    TripStatus tripStatus;
    LocalDateTime startTripTime;

    public Duration getTotalTimeToCompleteTrip() {
        if (startTripTime != null && endTripTime != null) {
            final Duration between = Duration.between(startTripTime, endTripTime);
            final long minutes = between.toMinutes();
            return between;
        }
        return null;
    }
}
