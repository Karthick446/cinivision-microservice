package com.example.karthick.userService.business.concretes;

import com.example.karthick.userService.business.abstracts.ClaimService;
import com.example.karthick.userService.business.abstracts.UserService;
import com.example.karthick.userService.dao.UserDao;
import com.example.karthick.userService.entity.Claim;
import com.example.karthick.userService.entity.User;
import com.example.karthick.userService.entity.dto.UserRegisterRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final ClaimService claimService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Boolean isUserExist(Long userId) {
        return userDao.findById(userId).isPresent();  // Use Optional to check existence
    }

    @Override
    public void addUser(UserRegisterRequestDto userRegisterRequestDto) {
        Claim claim = claimService.getClaimByClaimName("CUSTOMER");

        User user = User.builder()
                .email(userRegisterRequestDto.getEmail())
                .password(passwordEncoder.encode(userRegisterRequestDto.getPassword()))
                .fullName(userRegisterRequestDto.getCustomerName())
                .claim(claim)
                .build();

        userDao.save(user);  // Use save() instead of insert() for JPA
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.findByEmail(email);  // Use findByEmail (Spring Data JPA convention)
    }

    @Override
    public boolean isUserCustomer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream()
                .allMatch(a -> a.getAuthority().equals("ROLE_CUSTOMER"));
    }

    @Override
    public boolean isUserAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }
}
