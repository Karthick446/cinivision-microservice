package com.example.karthick.userService.controller;

import com.example.karthick.userService.business.abstracts.UserService;
import com.example.karthick.userService.entity.dto.UserRegisterRequestDto;

import antlr.collections.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.karthick.userService.entity.User;
@RestController
@RequestMapping("/api/user/users/")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("isExist/{userId}")
    public boolean isExists(@PathVariable Long userId) {
        return userService.isUserExist(userId);
    }

    @PostMapping("add")
    public void addUser(@RequestBody UserRegisterRequestDto userRegisterRequestDto) {
        userService.addUser(userRegisterRequestDto);
    }

    @GetMapping("isUserCustomer")
    public boolean isUserCustomer() {
        return userService.isUserCustomer();
    }
    @GetMapping("isUserAdmin")
    public boolean isUserAdmin() {
        return userService.isUserAdmin();
    }

}