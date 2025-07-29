package com.example.todo.todo.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @JsonProperty("_id")
    private String id;

    @NotNull(message = "This field is required")
    private String username;

    @Email(message = "Enter a valid email address")
    @NotNull(message = "This field is required")
    private String email;

    private String password;

    private String imagePath;
}
