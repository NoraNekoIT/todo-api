package com.demo.kly.todoapi.mockmvc;

import com.demo.kly.todoapi.controllers.TodoController;
import com.demo.kly.todoapi.payloads.TodoRequest;
import com.demo.kly.todoapi.services.TodoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodoControllerTest {
    private MockMvc mockMvc;

    @Mock
    private TodoService todoService;

    @InjectMocks
    private TodoController todoController;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void createTest() {
        TodoRequest todoRequest = new TodoRequest();
        todoRequest.setMessage("todo data");
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        ResponseEntity<?> responseEntity = todoController.create(todoRequest);
        Assertions.assertEquals(201,responseEntity.getStatusCodeValue());
        Mockito.verify(todoService, Mockito.times(1)).create(todoRequest);
    }

//    @Test
//    void updateTest(){
//        TodoRequest todoRequest = new TodoRequest();
//        todoRequest.setMessage("todo data");
//        MockHttpServletRequest request = new MockHttpServletRequest();
//        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
//        todoRequest.setMessage("todo data update");
//        ResponseEntity<?> responseEntity = todoController.update(todoRequest,1);
//        Assertions.assertEquals(200,responseEntity.getStatusCodeValue());
//    }
//
//    @Test
//    void deleteTest(){
//        MockHttpServletRequest request = new MockHttpServletRequest();
//        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
//        ResponseEntity<?> responseEntity = todoController.delete(1);
//        Assertions.assertEquals(
//                202,
//                responseEntity.getStatusCodeValue());
//    }
//
//    @Test
//    void getTest(){
//        MockHttpServletRequest request = new MockHttpServletRequest();
//        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
//        ResponseEntity<?> responseEntity = todoController.get(1);
//        Assertions.assertEquals(
//                200,
//                responseEntity.getStatusCodeValue());
//    }
//
//    @Test
//    void listTest(){
//        MockHttpServletRequest request = new MockHttpServletRequest();
//        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
//        ResponseEntity<?> responseEntity = todoController.lists();
//        Assertions.assertEquals(
//                200,
//                responseEntity.getStatusCodeValue());
//    }

}