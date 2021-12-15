package com.jpop.bookservice.controller;

import com.jpop.bookservice.model.Book;
import com.jpop.bookservice.model.OldBook;
import com.jpop.bookservice.service.BookService;
import exceptions.CustomDataNotFoundException;
import exceptions.CustomErrorException;
import exceptions.CustomParameterConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping( {"/v1.1","/v1.2"} )
    ResponseEntity<List<Book>>  getAllBooks(){
        List<Book> books;
        try {
             books = bookService.findAll();
            if( books.isEmpty() ){
                throw new NullPointerException("Data not found");
            }
        } catch (NullPointerException e) {
            throw new CustomDataNotFoundException(e.getMessage());
        }
        return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
    }

    @GetMapping({"/v1.0"})
    List<OldBook> getAllOldBooks(){
        return bookService.findAllOldBooks();
    }

    @GetMapping({"/v1.1/{id}","/v1.2/{id}"})
    ResponseEntity<Optional<Book>> getBooksById(@PathVariable("id") String id){
        Long longId;
        try {
            longId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new CustomParameterConstraintException("Input String cannot be parsed to Integer.");
        }
        return new ResponseEntity<Optional<Book>>(bookService.findById(longId), HttpStatus.OK);
    }

    @PostMapping({"/v1.0"})
    ResponseEntity<OldBook> addOldBooks(@RequestBody Optional<OldBook> book){
        if(!book.isPresent()) {
            throw new CustomErrorException(
                    HttpStatus.BAD_REQUEST,
                    "request object is empty!"
            );
        }
         return new ResponseEntity<OldBook>( bookService.save(book.get()),HttpStatus.OK);
    }

    @PostMapping({"/v1.2"})
    ResponseEntity<Book> addBooks(@RequestBody Optional<Book> book){
        if(!book.isPresent()) {
            throw new CustomErrorException(
                    HttpStatus.BAD_REQUEST,
                    "request object is empty!"
            );
        }
        return new ResponseEntity<Book> (bookService.save(book.get()),HttpStatus.OK);
    }

    @DeleteMapping({"/v1.0/test/{id}","/v1.1/{id}"})
    public ResponseEntity<Void> deleteBookByID(@PathVariable("id") Optional<String> id){
        Long longId;
        try {
            longId = Long.parseLong(id.get());
        } catch (NumberFormatException e) {
            throw new CustomParameterConstraintException("Input String cannot be parsed to Integer.");
        }
        bookService.deleteById(longId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping({"/v1.1/{id}"})
    ResponseEntity<Book> updateBooks(@RequestBody Optional<Book> book,@PathVariable("id") String id){
        if(!book.isPresent()) {
            throw new CustomErrorException(
                    HttpStatus.BAD_REQUEST,
                    "Parameters not passed"
            );
        }
        Long longId;
        try {
             longId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new CustomParameterConstraintException("Input String cannot be parsed to Integer.");
        }
        Optional<Book> entity =  bookService.findById(longId);
        if(entity.isPresent()){
           Book object =  entity.get();
            object.setAuthor(book.get().getAuthor());
            object.setCategory(book.get().getCategory());
            object.setDescription(book.get().getDescription());
            object.setTitle(book.get().getTitle());
            return  new ResponseEntity<Book>(bookService.save(object),HttpStatus.OK);
        }else
            return null;
    }

}