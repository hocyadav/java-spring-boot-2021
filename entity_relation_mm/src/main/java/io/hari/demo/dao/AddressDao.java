package io.hari.demo.dao;

import io.hari.demo.entity.Address;
import io.hari.demo.entity.Person;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressDao extends BaseDao<Address> {
}
