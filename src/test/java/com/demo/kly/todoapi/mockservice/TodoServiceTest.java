package com.demo.kly.todoapi.mockservice;

import com.demo.kly.todoapi.entities.TodoEntity;
import com.demo.kly.todoapi.payloads.TodoRequest;
import com.demo.kly.todoapi.repositories.TodoRepository;
import com.demo.kly.todoapi.services.TodoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoServiceImpl todoService;

    @Test
    void listTest() {
//        Assertions.assertNull(todoService.lists());
        List<TodoEntity> expected = new ArrayList<>(Arrays
                .asList(
                        new TodoEntity("todo 1"),
                        new TodoEntity("todo 2")
                ));
        Mockito.when(todoRepository.findAll()).thenReturn(expected);
//        Assertions.assertEquals("todo 1", todoService.lists().get(0).getMessage());
//        Assertions.assertEquals("todo 2", todoService.lists().get(1).getMessage());
        Assertions.assertSame(
                expected,
                todoService.lists());
        Mockito.verify(todoRepository,
                Mockito.times(1)).findAll();
    }

    @Test
    void deleteTest() {
        TodoEntity todo = new TodoEntity(
                "todo yg akan di delete"
        );
        Mockito.lenient().when(todoRepository.findById(1)).thenReturn(Optional.of(todo));
        todoService.delete(1);
        Mockito.verify(todoRepository, Mockito.times(1)).deleteById(1);
    }

    @Test
    void getTest() {
        TodoEntity todo = new TodoEntity(
                "test todo"
        );
        Mockito.lenient().when(todoRepository.findById(1)).thenReturn(Optional.of(todo));
        Assertions.assertEquals("test todo", todoService.get(1).getMessage());
        todoService.get(1);
        Mockito.verify(todoRepository, Mockito.times(2)).findById(1);
    }

    @Test
    void getTestAtNull(){
        Assertions.assertNull(todoService.get(1));
    }

    @Test
    void createTest() {
        TodoEntity todo = new TodoEntity();
        todo.setMessage("todo magang di kly");
        TodoRequest todoRequest = new TodoRequest();
        todoRequest.setMessage("todo magang di kly");
        Mockito.lenient().when(todoRepository.save(Mockito.any())).thenReturn(todo);
        TodoEntity createTodo = todoService.create(todoRequest);
        Assertions.assertEquals(
                todo.getMessage(),
                createTodo.getMessage());
        Mockito.verify(todoRepository, Mockito.times(1)).save(Mockito.any(TodoEntity.class));
    }

    @Test
    void updateTest(){
        TodoEntity todo = new TodoEntity(
                1,
                "todo magang di kly"
        );
        TodoRequest todoRequest = new TodoRequest();
        Mockito.lenient().when(todoRepository.findById(Mockito.any())).thenReturn(Optional.of(todo));
        todoRequest.setMessage("update todo");
        Mockito.lenient().when(todoRepository.save(Mockito.any())).thenReturn(todo);
        Assertions.assertEquals("update todo",
                todoService.update(todoRequest,1).getMessage());
        Mockito.verify(todoRepository, Mockito.times(1)).save(todo);
    }

}