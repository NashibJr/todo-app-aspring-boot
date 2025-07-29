package com.example.todo.todo.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @DBRef
    @JsonBackReference
    private List<TodoEntity> todos = new ArrayList<>();

    private String imagePath;
}
