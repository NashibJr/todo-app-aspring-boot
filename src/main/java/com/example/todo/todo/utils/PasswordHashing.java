package com.example.todo.todo.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.todo.todo.entities.UserEntity;

public class PasswordHashing {
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean checkPassword(String password, UserEntity user) {
        return passwordEncoder.matches(password, user.getPassword());
    }
}
