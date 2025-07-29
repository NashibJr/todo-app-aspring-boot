package com.example.todo.todo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.todo.todo.entities.TodoEntity;

public interface TodoRepository extends MongoRepository<TodoEntity, String> {

}
