package com.example.karthick.userService.dao;

import com.example.karthick.userService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {

    User findByUserId(Long userId);  // Changed ID type from String to Long

    User findByEmail(String email);  // Followed Spring Data JPA naming conventions
}

