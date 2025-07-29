package com.example.todo.todo.dtos;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class CreateUserDto {
    private String username;
    private String email;
    private String password;
    private MultipartFile image;
}
