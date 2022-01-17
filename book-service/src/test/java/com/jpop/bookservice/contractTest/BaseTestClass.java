package com.jpop.bookservice.contractTest;

import com.jpop.bookservice.BookServiceApplication;
import com.jpop.bookservice.controller.BookController;
import com.jpop.bookservice.model.Book;
import com.jpop.bookservice.service.BookService;
import io.restassured.RestAssured;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = BookServiceApplication.class )
public abstract class BaseTestClass {
    @Autowired
    BookController bookController;

    @MockBean
    BookService bookService;

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
        RestAssuredMockMvc.standaloneSetup(bookController);
        Mockito.when(bookService.findAll()).thenReturn( getMockBooksData());
    }

}