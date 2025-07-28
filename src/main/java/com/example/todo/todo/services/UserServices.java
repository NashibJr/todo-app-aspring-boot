package com.example.todo.todo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.todo.todo.dtos.LoginDto;
import com.example.todo.todo.dtos.Response;
import com.example.todo.todo.entities.UserEntity;
import com.example.todo.todo.repositories.UserRepository;
import com.example.todo.todo.utils.JwtUtil;
import com.example.todo.todo.utils.PasswordHashing;

@Service
public class UserServices {
    @Autowired
    UserRepository userRepository;

    public ResponseEntity<?> createUser(UserEntity userData) {
        try {
            UserEntity exists = userRepository.findByEmail(userData.getEmail());
            if (exists != null) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(new Response(
                                "User with this email already exists", ""));
            }

            PasswordHashing passwordHashing = new PasswordHashing();
            String hashedPassword = passwordHashing.hashPassword(userData.getPassword());
            userData.setPassword(hashedPassword);
            UserEntity user = userRepository.save(userData);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new Response(user, "Account successfully created"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new Response(e.getMessage(), ""));
        }
    }

    public ResponseEntity<List<UserEntity>> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    public ResponseEntity<UserEntity> getOneUser(String id) {
        UserEntity user = userRepository.findById(id).orElse(null);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    public ResponseEntity<Response> login(LoginDto loginDto) {
        try {
            UserEntity user = userRepository.findByEmail(loginDto.getEmail());
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new Response("User not found", ""));
            }

            PasswordHashing passwordHashing = new PasswordHashing();
            boolean checkPassword = passwordHashing.checkPassword(
                    loginDto.getPassword(),
                    user);
            if (!checkPassword) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                        new Response("Incorect bassword", ""));
            }

            JwtUtil jwtUtil = new JwtUtil();
            String token = jwtUtil.getToken(user.getId());

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                    new Response(user, "Login Successful", token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new Response(e.getMessage(), ""));
        }
    }
}
