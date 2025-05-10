package com.example.karthick.userService.business.abstracts;

import com.example.karthick.userService.entity.User;
import com.example.karthick.userService.entity.dto.UserRegisterRequestDto;

public interface UserService {

    Boolean isUserExist(Long userId);

    void addUser(UserRegisterRequestDto userRegisterRequestDto);

    User getUserByEmail(String email);

    boolean isUserCustomer();

    boolean isUserAdmin();

}