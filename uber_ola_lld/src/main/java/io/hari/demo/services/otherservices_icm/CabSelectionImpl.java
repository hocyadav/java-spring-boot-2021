package io.hari.demo.services.otherservices_icm;

import io.hari.demo.config.AppConfig;
import io.hari.demo.dao.CabLockDao;
import io.hari.demo.entity.Cab;
import io.hari.demo.entity.CabLock;
import io.hari.demo.entity.CabLockStatus;
import io.hari.demo.entity.User;
import lombok.SneakyThrows;
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
public class CabSelectionImpl implements ICabSelection {//ICM : interface, class and method

    @Autowired
    CabLockDao cabLockDao;
    @Autowired
    AppConfig config;

    @Override
    @SneakyThrows
    public Optional<Cab> selectSingleBestCab(User user, List<Cab> cabs) {

        final Optional<Cab> first = cabs.stream().findFirst();
        if (first.isPresent()) {
            final Cab cab = first.get();
            temporarilyLockCabFor30sec(cab);
            Thread.sleep(1000*20);//driver is taking time to select user : in this time interval lock is in temp status with timeout
        }
        return first;
    }

    private void temporarilyLockCabFor30sec(Cab cab) {
        final CabLock cabLock = cabLockDao.findByCabId(cab.getId());
        cabLock.setLockStatus(CabLockStatus.temp_locked);
        cabLock.setLockTime(LocalDateTime.now());
//        cabLock.setTimeout(Long.valueOf(30));
        cabLock.setTimeout(config.getTempCabLockTime());//read from config
        cabLockDao.save(cabLock);
    }
}
