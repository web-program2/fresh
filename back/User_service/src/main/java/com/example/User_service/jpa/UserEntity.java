package com.example.User_service.jpa;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="user")
public class UserEntity {
    @Id
    @GeneratedValue
    private Long user_idx;

    @Column(nullable = false, unique = true)
    private String id;

    @Column(nullable = false, unique = true)
    private String pw;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String role;



}
