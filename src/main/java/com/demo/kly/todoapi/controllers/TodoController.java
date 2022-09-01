package com.demo.kly.todoapi.controllers;

import com.demo.kly.todoapi.entities.TodoEntity;
import com.demo.kly.todoapi.payloads.ResponseHandler;
import com.demo.kly.todoapi.payloads.TodoRequest;
import com.demo.kly.todoapi.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/")
public class TodoController {
    @Autowired
    TodoService todoService;

    // Add
    @PostMapping(value = "todo")
    public ResponseEntity<Object> create(@RequestBody TodoRequest params) {
        try {
            TodoEntity result = todoService.create(params);
            return ResponseHandler.generateResponse("Successfully added data!", HttpStatus.CREATED, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }

    }

    // Get
    @GetMapping(value = "/todo")
    public ResponseEntity<Object> lists() {
        try {
            List<TodoEntity> result = todoService.lists();
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
        }
    }

    // Get By ID
    @GetMapping(value = "/todo/{id}")
    public ResponseEntity<Object> get(@PathVariable int id) {
        try {
            TodoEntity result = todoService.get(id);
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
        }
    }

    // Update
    @PutMapping(value = "/todo/{id}")
    public ResponseEntity<Object> update(@RequestBody TodoRequest params, @PathVariable int id) {
        try {
            TodoEntity result = todoService.update(params,id);
            return ResponseHandler.generateResponse("Updated", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
        }
    }

    // Delete
    @DeleteMapping(value = "/todo/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        try {
            String result = todoService.delete(id);
            return ResponseHandler.generateResponse("Deleted!", HttpStatus.ACCEPTED, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
        }
    }

}
