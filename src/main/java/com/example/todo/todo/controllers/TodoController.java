package com.example.todo.todo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.todo.todo.dtos.CreateTodDto;
import com.example.todo.todo.dtos.Response;
import com.example.todo.todo.services.TodoServices;

@RestController
@RequestMapping("api/v1/todos")
public class TodoController {
    @Autowired
    TodoServices todoServices;

    @PostMapping("/create")
    ResponseEntity<?> create(@RequestBody CreateTodDto todo) {
        try {
            return todoServices.create(todo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    new Response(e.getMessage(), ""));
        }
    }
}
