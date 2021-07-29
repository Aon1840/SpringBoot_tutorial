package com.bearman.demo.backend.repository;

import com.bearman.demo.backend.entity.Social;
import com.bearman.demo.backend.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SocialRepository extends CrudRepository<Social, String> {

    Optional<Social> findByUser(User user);
}
