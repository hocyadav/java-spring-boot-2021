package io.hari.demo.dao;

import io.hari.demo.entity.onetomanybi.Seat;
import io.hari.demo.entity.onetomanybi.Show;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatDao extends BaseDao<Seat> {
}
