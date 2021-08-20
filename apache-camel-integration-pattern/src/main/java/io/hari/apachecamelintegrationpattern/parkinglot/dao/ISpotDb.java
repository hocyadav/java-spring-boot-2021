package io.hari.apachecamelintegrationpattern.parkinglot.dao;

import io.hari.apachecamelintegrationpattern.parkinglot.entity.Spot;

public interface ISpotDb {
    void create(Spot spot);

    void update(Spot spot);

    void delete(Spot spot);
}
