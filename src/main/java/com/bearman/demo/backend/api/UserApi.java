package com.bearman.demo.backend.api;

import com.bearman.demo.backend.business.UserBusiness;
import com.bearman.demo.backend.entity.User;
import com.bearman.demo.backend.exception.FileException;
import com.bearman.demo.backend.exception.UserException;
import com.bearman.demo.backend.model.RegisterRequest;
import com.bearman.demo.backend.model.TestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
public class UserApi {

    // method1 => Field Injection
    @Autowired
//    private TestBusiness business;

    // method2 => Constructor Injection
    private final UserBusiness business;

    public UserApi(UserBusiness business) {
        this.business = business;
    }


    @GetMapping
    public TestResponse test() {
        TestResponse response = new TestResponse();
        response.setFood("Rice");
        response.setName("Aon");
        return response;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest request) throws UserException {
        User response = business.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<String> uploadProfilePicture(@RequestPart MultipartFile file) throws FileException {
        String response = business.uploadProfilePicture(file);
        return ResponseEntity.ok(response);
    }
}