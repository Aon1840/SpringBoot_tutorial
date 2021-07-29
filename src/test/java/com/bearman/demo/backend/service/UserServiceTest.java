package com.bearman.demo.backend.service;

import com.bearman.demo.backend.entity.Address;
import com.bearman.demo.backend.entity.Social;
import com.bearman.demo.backend.entity.User;
import com.bearman.demo.backend.exception.UserException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private SocialService socialService;

    @Autowired
    private AddressService addressService;

    @Order(1)
    @Test
    void testCreate() throws UserException {
        User user = userService.create(
                TestCreateData.email,
                TestCreateData.password,
                TestCreateData.name);

        // check not null
        Assertions.assertNotNull(user);
        Assertions.assertNotNull(user.getId());

        // check equals
        Assertions.assertEquals(TestCreateData.name, user.getName());
        Assertions.assertEquals(TestCreateData.email, user.getEmail());

        boolean isMatched = userService.matchPassword(TestCreateData.password, user.getPassword());
        Assertions.assertTrue(isMatched);
    }

    @Order(2)
    @Test
    void testUpdate() throws UserException {
        Optional<User> opt = userService.findByEmail(TestCreateData.email);
        Assertions.assertTrue(opt.isPresent());

        User user = opt.get();

        User updateUser = userService.updateName(user.getId(), TestUpdateData.name);

        Assertions.assertNotNull(updateUser);
        Assertions.assertEquals(TestUpdateData.name, updateUser.getName());
    }

    @Order(3)
    @Test
    void testCreateSocial() {
        Optional<User> opt = userService.findByEmail(TestCreateData.email);
        Assertions.assertTrue(opt.isPresent());

        User user = opt.get();

        Social social = user.getSocial();
        Assertions.assertNull(social);

        social = socialService.create(
                user,
                SocialTestCreateData.facebook,
                SocialTestCreateData.line,
                SocialTestCreateData.instagram,
                SocialTestCreateData.tiktok);

        Assertions.assertNotNull(social);
        Assertions.assertEquals(SocialTestCreateData.facebook, social.getFacebook());
    }

    @Order(4)
    @Test
    void testCreateAddress() {
        Optional<User> opt = userService.findByEmail(TestCreateData.email);
        Assertions.assertTrue(opt.isPresent());

        User user = opt.get();

        List<Address> addresses = user.getAddress();
        Assertions.assertTrue(addresses.isEmpty());

        Address address = addressService.create(
                user,
                AddressTestCreateData.line1,
                AddressTestCreateData.line2,
                AddressTestCreateData.zipcode);

        Assertions.assertNotNull(address);
        Assertions.assertEquals(AddressTestCreateData.line1, address.getLine1());
    }

    @Order(9)
    @Test
    void testDelete() {
        Optional<User> opt = userService.findByEmail(TestCreateData.email);
        Assertions.assertTrue(opt.isPresent());

        User user = opt.get();
        userService.deleteById(user.getId());

        Optional<User> optDelete = userService.findByEmail(TestCreateData.email);
        Assertions.assertTrue(optDelete.isEmpty());
    }

    interface TestCreateData {
        String email = "a@gmail.com";
        String password = "123456";
        String name = "test";
    }

    interface TestUpdateData {
        String name = "aon";
    }

    interface SocialTestCreateData {
        String facebook = "Aon1840";
        String line = "Aon1840";
        String instagram = "Aon1840";
        String tiktok = "Aon1840";
    }

    interface AddressTestCreateData {
        String line1 = "line1";
        String line2 = "line2";
        String zipcode = "zipcode";
    }
}