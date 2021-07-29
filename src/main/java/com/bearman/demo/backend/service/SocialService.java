package com.bearman.demo.backend.service;

import com.bearman.demo.backend.entity.Social;
import com.bearman.demo.backend.entity.User;
import com.bearman.demo.backend.exception.UserException;
import com.bearman.demo.backend.repository.SocialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class SocialService {

    @Autowired
    private SocialRepository repository;

    public Optional<Social> findByUser(User user) {
        return repository.findByUser(user);
    }

    public Social create(User user,
                         String facebook,
                         String line,
                         String instagram,
                         String tiktok) {

        // TODO: validate
        Social entity = new Social();

        entity.setUser(user);
        entity.setFacebook(facebook);
        entity.setLine(line);
        entity.setInstagram(instagram);
        entity.setTictok(tiktok);

        return repository.save(entity);
    }

}
