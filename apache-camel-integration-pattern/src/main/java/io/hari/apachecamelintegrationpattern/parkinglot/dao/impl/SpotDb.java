package io.hari.apachecamelintegrationpattern.parkinglot.dao.impl;

import io.hari.apachecamelintegrationpattern.parkinglot.dao.ISpotDb;
import io.hari.apachecamelintegrationpattern.parkinglot.entity.Spot;

import java.util.HashMap;
import java.util.Map;

public class SpotDb implements ISpotDb {
    Map<Integer, Spot> map = new HashMap<>();

    @Override
    public void create(Spot spot) {
        map.put(spot.getId(), spot);
    }

    @Override
    public void update(Spot spot) {
        map.put(spot.getId(), spot);
    }

    @Override
    public void delete(Spot spot) {
        //
    }
}
