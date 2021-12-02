package com.jpop.bookservice.controller;

import com.jpop.bookservice.model.Book;
import com.jpop.bookservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("books")
    List<Book> getAllBooks(){
        return bookService.findAll();
    }

    @GetMapping("books/{id}")
    Optional<Book> getBooksById(@PathVariable("id") Long id){
        return bookService.findById(id);
    }

    @PostMapping("books")
    Book addBooks(@RequestBody Book book){
         return bookService.save(book);
    }

    @DeleteMapping("books/{id}")
    void deleteBookByID(@PathVariable("id") Long id){
        bookService.deleteById(id);
    }

    @PutMapping("books/{id}")
    Book updateBooks(@RequestBody Book book,@PathVariable("id") Long id){
        Optional<Book> entity =  bookService.findById(id);
        if(entity.isPresent()){
           Book object =  entity.get();
            object.setAuthor(book.getAuthor());
            object.setCategory(book.getCategory());
            object.setDescription(book.getDescription());
            object.setTitle(book.getTitle());
            return bookService.save(object);
        }else
            return null;
    }

}