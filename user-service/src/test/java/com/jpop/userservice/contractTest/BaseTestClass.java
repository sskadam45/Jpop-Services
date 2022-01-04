package com.jpop.userservice.contractTest;

import com.jpop.userservice.UserServiceApplication;
import com.jpop.userservice.controller.UserController;
import com.jpop.userservice.model.Book;
import com.jpop.userservice.service.UserService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = UserServiceApplication.class )
public abstract class BaseTestClass {
    @Autowired
    UserController userController;

    @MockBean
    UserService userService;

    List<Book> mockList = new ArrayList<>();
    public List<Book> getMockBooksData(){
        Book mockBook1 = new Book();
        mockBook1.setAuthor("a1");
        mockBook1.setTitle("mr");
        mockBook1.setCategory("Love2");
        mockBook1.setDescription("its fine");
        mockBook1.setId(28);

        mockList.add(mockBook1);
        return mockList;
    }

    @BeforeEach()
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(userController);
        Mockito.when(userService.getUserWithBooks()).thenReturn( getMockBooksData());
    }

}