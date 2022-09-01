package com.demo.kly.todoapi.services;

import com.demo.kly.todoapi.entities.TodoEntity;
import com.demo.kly.todoapi.payloads.TodoRequest;

import java.util.List;

public interface TodoService {

    TodoEntity create(TodoRequest todoRequest);

    TodoEntity get(int id);

    TodoEntity update(TodoRequest todoRequest, int id);

    String delete(int id);

    List<TodoEntity> lists();

}