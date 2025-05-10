package com.example.karthick.userService.dao;

import com.example.karthick.userService.entity.Claim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimDao extends JpaRepository<Claim, Long> {

    Claim findByClaimName(String claimName);  // Renamed to follow Spring Data JPA conventions
}
