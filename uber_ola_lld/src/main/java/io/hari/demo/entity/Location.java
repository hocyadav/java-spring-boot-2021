package io.hari.demo.entity;

import lombok.*;

/**
 * @Author Hariom Yadav
 * @create 02-04-2021
 */
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {})
@AllArgsConstructor
@Builder
public class Location {
    Double x;
    Double y;

    public double distanceBetweenLocation(Location location) {
        final Double x = location.getX();
        final Double y = location.getY();
        final double sqrt = Math.sqrt(
                Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));
        return sqrt;
    }
}
