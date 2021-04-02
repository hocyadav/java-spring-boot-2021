package io.hari.demo.services.otherservices_icm;

import io.hari.demo.config.AppConfig;
import io.hari.demo.entity.Location;
import io.hari.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Hariom Yadav
 * @create 02-04-2021
 */
@Service
public class PriceSelectionStrategyImpl implements IPriceSelectionStrategy {

    public static final int PER_KM_RATE = 10;

    @Autowired
    AppConfig config;

    @Override
    public double getBestPrice(User user, Location toLocation) {
//        final double price = user.getLocation().distanceBetweenLocation(toLocation) * PER_KM_RATE;
        final double price = user.getLocation().distanceBetweenLocation(toLocation) * config.getPerKmPrice();//read from config
        return price;
    }
}
