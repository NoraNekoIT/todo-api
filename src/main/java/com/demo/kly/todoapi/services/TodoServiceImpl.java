package com.demo.kly.todoapi.services;

import com.demo.kly.todoapi.entities.TodoEntity;
import com.demo.kly.todoapi.payloads.TodoRequest;
import com.demo.kly.todoapi.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public TodoEntity create(TodoRequest todoRequest) {
        TodoEntity todoInsert = new TodoEntity();
        todoInsert.setMessage(todoRequest.getMessage());
        return todoRepository.save(todoInsert);
    }

    @Override
    public TodoEntity get(int id) {
        return todoRepository.findById(id).orElse(null);
    }

    @Override
    public TodoEntity update(TodoRequest todoRequest, int id) {
        TodoEntity todoUpdate = todoRepository.findById(id).orElse(null);
        todoUpdate.setMessage(todoRequest.getMessage());
        return todoRepository.save(todoUpdate);
    }

    @Override
    public String delete(int id) {
        todoRepository.deleteById(id);
        return " Deleted " + id;
    }

    @Override
    public List<TodoEntity> lists() {
//        todoRepository.findAll();
        return todoRepository.findAll();
    }
}