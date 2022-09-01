package com.demo.kly.todoapi.withoutMock;

import com.demo.kly.todoapi.services.TodoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DeleteTodoTest {

    @Autowired
    private TodoService todoService;

    @Test
    void deleteTest() {
        Assertions.assertEquals(" Deleted 1", todoService.delete(1));
    }
}