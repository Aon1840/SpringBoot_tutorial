package com.bearman.demo.backend.business;

import com.bearman.demo.backend.entity.User;
import com.bearman.demo.backend.exception.FileException;
import com.bearman.demo.backend.exception.UserException;
import com.bearman.demo.backend.mapper.UserMapper;
import com.bearman.demo.backend.model.LoginRequest;
import com.bearman.demo.backend.model.RegisterRequest;
import com.bearman.demo.backend.model.RegisterResponse;
import com.bearman.demo.backend.service.TokenService;
import com.bearman.demo.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserBusiness {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TokenService tokenService;

    public String login(LoginRequest request) throws UserException {
        // validate request

        // verify database
        Optional<User> opt = userService.findByEmail(request.getEmail());
        if (opt.isEmpty()) {
            throw UserException.loginFailEmailNotFound();
        }

        User user = opt.get();
        if (!userService.matchPassword(request.getPassword(), user.getPassword())) {
            throw UserException.loginFailPasswordIncorrect();
        }

        return tokenService.tokenize(user);
    }

    public RegisterResponse register(RegisterRequest request) throws UserException {
        User user = userService.create(request.getEmail(), request.getPassword(), request.getName());

        return userMapper.toRegisterResponse(user);
    }

    public String uploadProfilePicture(MultipartFile file) throws FileException {
        // validate file
        if (file == null) {
            // throw error
            throw FileException.fileNull();
        }

        // validate size
        if (file.getSize() > (1048576 * 2)) {
            // throw error
            throw FileException.fileMaxSize();
        }

        // validate type
        String contentType = file.getContentType();
        if (contentType == null) {
            // throw error
            throw FileException.fileUnSupport();
        }

        List<String> supportedTypes = Arrays.asList("image/jpeg", "image/png");
        if (supportedTypes.contains(contentType)) {
            // throw error (un support type)
            throw FileException.fileUnSupport();
        }

        // TODO: upload file to File storage (s3)
        try {
            byte[] bytes = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
