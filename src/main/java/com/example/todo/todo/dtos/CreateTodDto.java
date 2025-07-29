package com.example.todo.todo.dtos;

import lombok.Data;

@Data
public class CreateTodDto {
    private String title;
    private String body;
    private String creatorId;
}
