package io.hari.apachecamelintegrationpattern.parkinglot.dao;

import io.hari.apachecamelintegrationpattern.parkinglot.entity.Floor;

public interface IFloorDb {
    void create(Floor floor);

    void update(Floor floor);

    void delete(Floor floor);
}
