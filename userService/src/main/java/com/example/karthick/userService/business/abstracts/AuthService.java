package com.example.karthick.userService.business.abstracts;

import com.example.karthick.userService.entity.dto.UserAuthenticationResponseDto;
import com.example.karthick.userService.entity.dto.UserLoginRequestDto;

public interface AuthService {

    UserAuthenticationResponseDto login(UserLoginRequestDto userLoginRequestDto);

}