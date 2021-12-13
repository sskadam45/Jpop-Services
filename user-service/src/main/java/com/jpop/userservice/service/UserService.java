package com.jpop.userservice.service;

import com.jpop.userservice.model.Book;
import com.jpop.userservice.model.BookProxy;
import com.jpop.userservice.model.User;
import com.jpop.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookProxy bookProxy;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public List<Book> getUserWithBooks() {
        return bookProxy.findAll();
    }
}
