package com.example.todo.todo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.todo.todo.dtos.CreateUserDto;
import com.example.todo.todo.dtos.LoginDto;
import com.example.todo.todo.dtos.Response;
import com.example.todo.todo.entities.UserEntity;
import com.example.todo.todo.services.UserServices;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    @Autowired
    UserServices userServices;

    @PostMapping("/create")
    ResponseEntity<?> create(@ModelAttribute CreateUserDto userData) {
        return userServices.createUser(userData);
    }

    @GetMapping("/all")
    ResponseEntity<List<UserEntity>> getAllUsers() {
        return userServices.getAllUsers();
    }

    @GetMapping("/{id}")
    ResponseEntity<UserEntity> getOneUser(@PathVariable String id) {
        return userServices.getOneUser(id);
    }

    @PostMapping("/auth/login")
    ResponseEntity<Response> login(@RequestBody LoginDto loginDto) {
        return userServices.login(loginDto);
    }
}
