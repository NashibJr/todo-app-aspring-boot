package com.example.todo.todo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.todo.todo.dtos.CreateTodDto;
import com.example.todo.todo.dtos.Response;
import com.example.todo.todo.entities.TodoEntity;
import com.example.todo.todo.entities.UserEntity;
import com.example.todo.todo.repositories.TodoRepository;
import com.example.todo.todo.repositories.UserRepository;

@Service
public class TodoServices {
    @Autowired
    TodoRepository todoRepository;

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<Response> create(CreateTodDto todoData) {
        try {
            UserEntity user = userRepository.findById(
                    todoData.getCreatorId()).orElse(null);
            if (user == null) {
                return ResponseEntity.status(404).body(
                        new Response("User not found", ""));
            }

            TodoEntity todo = new TodoEntity();
            todo.setCreator(user);
            todo.setTitle(todoData.getTitle());
            todo.setBody(todoData.getBody());
            TodoEntity createTodo = todoRepository.save(todo);

            user.getTodos().add(todo);
            userRepository.save(user);

            return ResponseEntity.status(201).body(
                    new Response(createTodo, "Todo successfully created"));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(
                    new Response(e.getMessage(), ""));
        }
    }
}
