package io.hari.demo.services.otherservices_icm;

import io.hari.demo.entity.Cab;
import io.hari.demo.entity.User;
import lombok.SneakyThrows;

import java.util.List;
import java.util.Optional;

/**
 * @Author Hariom Yadav
 * @create 02-04-2021
 */
public interface ICabSelection {
    @SneakyThrows
    Optional<Cab> selectSingleBestCab(User user, List<Cab> cabs);
}
