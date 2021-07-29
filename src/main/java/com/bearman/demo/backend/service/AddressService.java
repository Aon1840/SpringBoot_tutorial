package com.bearman.demo.backend.service;

import com.bearman.demo.backend.entity.Address;
import com.bearman.demo.backend.entity.Social;
import com.bearman.demo.backend.entity.User;
import com.bearman.demo.backend.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository repository;

    public List<Address> findByUser(User user) {
        return repository.findByUser(user);
    }

    public Address create(User user,
                          String line1,
                          String line2,
                          String zipcode) {

        // TODO: validate
        Address entity = new Address();

        entity.setUser(user);
        entity.setLine1(line1);
        entity.setLine2(line2);
        entity.setZipcode(zipcode);

        return repository.save(entity);
    }

}
