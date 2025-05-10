package com.example.karthick.userService.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor  // ✅ Required for Hibernate
@AllArgsConstructor
@Table(name = "users")  // Ensure the table name is correctly mapped
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;  // Changed from String to Long for MySQL auto-increment

    @Column(unique = true, nullable = false)  // Replaces @Indexed(unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String fullName;

    @ManyToOne  // User has a Claim (many users can have one claim)
    @JoinColumn(name = "claim_id")  // Foreign key to Claim entity
    private Claim claim;
}
