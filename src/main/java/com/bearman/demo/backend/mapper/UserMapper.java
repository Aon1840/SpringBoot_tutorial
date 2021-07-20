package com.bearman.demo.backend.mapper;

import com.bearman.demo.backend.entity.User;
import com.bearman.demo.backend.model.RegisterResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    RegisterResponse toRegisterResponse(User user);
}
