package io.hari.demo.services;

import io.hari.demo.convertor.TripStatus;
import io.hari.demo.dao.CabDao;
import io.hari.demo.dao.CabLockDao;
import io.hari.demo.dao.TripDao;
import io.hari.demo.entity.*;
import io.hari.demo.services.otherservices_icm.CabSelectionService;
import io.hari.demo.services.otherservices_icm.PriceSelectionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * @Author Hariom Yadav
 * @create 02-04-2021
 */
@Service
public class UserService {
    @Autowired
    TripDao tripDao;

    @Autowired
    CabLockDao cabLockDao;

    @Autowired
    CabDao cabDao;

    @Autowired
    CabSelectionService cabSelectionService;

    @Autowired
    PriceSelectionStrategy priceSelectionStrategy;

    public List<Trip> fetchTripsHistory(Long userId) {
        final List<Trip> trips = tripDao.findAllByUserId(userId);
        return trips;
    }

    public void findAvailableCabs(User user, Location toLocation) {
        final double userSearchRadius = user.getLocation().distanceBetweenLocation(toLocation);
        findAvailableCabs(user, userSearchRadius);
    }

    public List<Cab> findAvailableCabs(User user, Double userSearchRadius) {
        final List<CabLock> fetchedCabLocks = cabLockDao.findAvailableCabs();
        List<Cab> cabs = new LinkedList<>();

        fetchedCabLocks.forEach(cabLock -> {
//            cabDao.findById(cabLock.getCabId()).filter(i -> i.getLocation().distanceBetweenLocation(userLocation) <= userSearchRadius);
            cabDao.findById(cabLock.getCabId()).ifPresent(cab -> {
                final double userAndCabDistance = cab.getLocation().distanceBetweenLocation(user.getLocation());
                System.out.println(cab.getId()+" userAndCabDistance = " + userAndCabDistance);
                if (cab.getStatus().equals("available") && userAndCabDistance <= userSearchRadius) {//cab.getStatus().equals("available") added to filter out sleeping cabs
                    cabs.add(cab);
                }
            });
        });
        return cabs;
    }

    public synchronized Trip bookTrip(User user, List<Cab> cabs, Location toLocation) {
//        final Optional<Cab> optionalCab = cabs.stream().findAny();//TODO ICM
        final Optional<Cab> optionalCab = cabSelectionService.selectSingleBestCab(user, cabs);//done TODO ICM

        if (!optionalCab.isPresent()) {
            System.out.println("no cab");
            return null;
        }

        final Cab cab = optionalCab.get();
//        final double price = user.getLocation().distanceBetweenLocation(toLocation) * 10;//TODO done ICM
        final double price = priceSelectionStrategy.getBestPrice(user, toLocation);
        final Trip trip = Trip.builder()
                .cabId(cab.getId())
                .userId(user.getId())
                .fromLocation(user.getLocation())
                .toLocation(toLocation)
                .tripStatus(TripStatus.STARTED_FROM_SOURCE)
                .price(BigDecimal.valueOf(price)).build();

        final Trip trip1 = tripDao.save(trip);
        changeCabLockStatus(cab, CabLockStatus.locked);
        return trip1;
    }

    private synchronized void changeCabLockStatus(Cab cab, CabLockStatus cabLockStatus) {
        final CabLock cabLock = cabLockDao.findByCabId(cab.getId());
        cabLock.setLockStatus(cabLockStatus);
        cabLockDao.save(cabLock);
    }
}
