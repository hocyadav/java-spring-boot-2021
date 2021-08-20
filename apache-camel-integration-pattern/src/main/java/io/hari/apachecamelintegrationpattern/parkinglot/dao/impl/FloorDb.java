package io.hari.apachecamelintegrationpattern.parkinglot.dao.impl;

import io.hari.apachecamelintegrationpattern.parkinglot.dao.IFloorDb;
import io.hari.apachecamelintegrationpattern.parkinglot.entity.Floor;

import java.util.HashMap;
import java.util.Map;

public class FloorDb implements IFloorDb {
    Map<Integer, Floor> map = new HashMap<>();

    @Override
    public void create(Floor floor) {
        map.put(floor.getId(), floor);
    }

    @Override
    public void update(Floor floor) {
        map.put(floor.getId(), floor);
    }

    @Override
    public void delete(Floor floor) {
        //
    }
}
