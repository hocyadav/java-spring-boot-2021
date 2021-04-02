package io.hari.demo.services;

import io.hari.demo.convertor.TripStatus;
import io.hari.demo.dao.CabDao;
import io.hari.demo.dao.CabLockDao;
import io.hari.demo.dao.TripDao;
import io.hari.demo.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @Author Hariom Yadav
 * @create 02-04-2021
 */
@Service
public class CabService {
    @Autowired
    CabDao cabDao;

    @Autowired
    CabLockDao cabLockDao;

    @Autowired
    TripDao tripDao;

    public void addCab(Cab cab) {
        final Cab cab1 = cabDao.save(cab);
        final CabLock cabLock = cabLockDao.findByCabId(cab1.getId());
        if (cabLock == null) {
            final CabLock cabLock1 = cabLockDao.save(
                    CabLock.builder()
                            .cabId(cab1.getId())
                            .lockStatus(CabLockStatus.available)
//                            .lockTime(LocalDateTime.now())
//                            .timeout(Long.valueOf(10))
                            .build());
            System.out.println("newly added cabLock1 = " + cabLock1);
        }
    }

    public List<Trip> fetchCabHistory(Long cabId) {
        final List<Trip> trips = tripDao.findAllByCabId(cabId);
        return trips;
    }

    public void changeCabStatus(Cab cab, String cabStatus) {
        final Optional<Cab> optionalCab = cabDao.findById(cab.getId());
        if (!optionalCab.isPresent()) {//todo exception
            System.out.println("cab not found");
            return;
        }
        final Cab cab1 = optionalCab.get();
        cab1.setStatus(cabStatus);
        cabDao.save(cab1);
    }

    public void endTrip(Trip trip) {
        trip.setTripStatus(TripStatus.COMPLETED);
        trip.setEndTripTime(LocalDateTime.now());
        tripDao.save(trip);
        unlockCab(trip.getCabId());
    }

    private synchronized void unlockCab(Long cabId) {
        final CabLock cabLock = cabLockDao.findByCabId(cabId);
        cabLock.setLockStatus(CabLockStatus.available);
        cabLockDao.save(cabLock);
    }

    public void changeCabLocation(Cab cab, Location newCabLocation) {
        cab.setLocation(newCabLocation);
        cabDao.save(cab);
    }
}
