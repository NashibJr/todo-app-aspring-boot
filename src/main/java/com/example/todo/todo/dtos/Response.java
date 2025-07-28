package com.example.todo.todo.dtos;

import java.util.List;

import lombok.Data;

@Data
public class Response {
    private String message;
    private String error;
    private Object object;
    private List<Object> objects;

    public Response() {
    }

    public Response(Object object, String message) {
        this.object = object;
        this.message = message;
    }

    public Response(String error, String message) {
        this.error = error;
        this.message = message;
    }

    public Response(String error) {
        this.message = error;
    }

    public Response(Object object) {
        this.object = object;
    }
}
