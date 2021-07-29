package com.bearman.demo.backend.repository;

import com.bearman.demo.backend.entity.Address;
import com.bearman.demo.backend.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AddressRepository extends CrudRepository<Address, String> {

    List<Address> findByUser(User user);
}
