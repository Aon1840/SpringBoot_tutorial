package com.bearman.demo.backend.business;

import com.bearman.demo.backend.entity.User;
import com.bearman.demo.backend.exception.FileException;
import com.bearman.demo.backend.exception.UserException;
import com.bearman.demo.backend.model.RegisterRequest;
import com.bearman.demo.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UserBusiness {

    @Autowired
    private UserService userService;

    public User register(RegisterRequest request) throws UserException {
        User user = userService.create(request.getEmail(), request.getPassword(), request.getName());
        // TODO: mapper
        return user;
    }

    public String uploadProfilePicture(MultipartFile file) throws FileException {
        // validate file
        if (file == null) {
            // throw error
            throw  FileException.fileNull();
        }

        // validate size
        if (file.getSize() > (1048576 * 2)) {
            // throw error
            throw  FileException.fileMaxSize();
        }

        // validate type
        String contentType = file.getContentType();
        if (contentType == null) {
            // throw error
            throw  FileException.fileUnSupport();
        }

        List<String> supportedTypes = Arrays.asList("image/jpeg", "image/png");
        if (supportedTypes.contains(contentType)) {
            // throw error (un support type)
            throw  FileException.fileUnSupport();
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
