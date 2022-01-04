package com.jpop.bookservice.service;

import com.jpop.bookservice.model.Book;
import com.jpop.bookservice.model.OldBook;
import com.jpop.bookservice.repository.BookRepository;
import com.jpop.bookservice.repository.OldBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    OldBookRepository oldBookRepository;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }
    public List<OldBook> findAllOldBooks() {
        return oldBookRepository.findAll();
    }
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public OldBook save(OldBook book) {
        return oldBookRepository.save(book);
    }
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
