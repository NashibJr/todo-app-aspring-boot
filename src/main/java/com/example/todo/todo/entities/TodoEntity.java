package com.example.todo.todo.entities;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class TodoEntity {
    @Id
    @JsonProperty("_id")
    private String id;

    @Size(min = 5, message = "It should be atleast 5 characters long")
    @NotNull(message = "This field is required")
    @NotBlank(message = "This field is required")
    private String title;

    @NotNull(message = "This field is required")
    @NotBlank(message = "This field is required")
    private String body;

    @DBRef
    @JsonManagedReference
    private UserEntity creator;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;
}
