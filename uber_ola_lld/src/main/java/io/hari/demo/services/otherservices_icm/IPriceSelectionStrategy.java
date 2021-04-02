package io.hari.demo.services.otherservices_icm;

import io.hari.demo.entity.Location;
import io.hari.demo.entity.User;

/**
 * @Author Hariom Yadav
 * @create 02-04-2021
 */
public interface IPriceSelectionStrategy {
    double getBestPrice(User user, Location toLocation);
}
