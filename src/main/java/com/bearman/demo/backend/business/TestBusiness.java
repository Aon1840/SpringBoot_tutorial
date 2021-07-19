package com.bearman.demo.backend.business;

import com.bearman.demo.backend.exception.UserException;
import com.bearman.demo.backend.model.RegisterRequest;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TestBusiness {

    public String register(RegisterRequest request) throws UserException {
        if (request == null) {
            throw UserException.requestNull();
        }

        // validate email
        if (Objects.isNull(request.getEmail())) {
            throw UserException.emailNull();
        }
        return "";
    }
}
