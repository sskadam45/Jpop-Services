package com.jpop.userservice.controller;

import com.jpop.userservice.model.Book;
import com.jpop.userservice.model.User;
import com.jpop.userservice.service.UserService;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/v1.1")
    ResponseEntity<List<User>>   getAllUsers(){
        List<User> users;
        try {
            users = userService.findAll();
            if( users.isEmpty() ){
                throw new NullPointerException("Data not found");
            }
        } catch (NullPointerException e) {
            throw new CustomDataNotFoundException(e.getMessage());
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @GetMapping("/v1.1/usersWithBooks")
    List<Book> getAllUsersBoOks(){
        return userService.getUserWithBooks();
    }


    @PostMapping("/v1.1")
    ResponseEntity<User> addUser(@RequestBody Optional<User> user){
        if(!user.isPresent()) {
            throw new CustomErrorException(
                    HttpStatus.BAD_REQUEST,
                    "request Body object is empty!"
            );
        }
        return new ResponseEntity<User>( userService.save(user.get()),HttpStatus.OK);
    }

    @PutMapping("/v1.1/{id}")
    User updateBooks(@RequestBody User user,@PathVariable("id") String id){
        Long longId;
        try {
            longId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new CustomParameterConstraintException("Input String cannot be parsed to Integer.");
        }
        Optional<User> entity =  userService.findById(longId);
        if(entity.isPresent()){
            User object =  entity.get();
            object.setTitle(user.getTitle());
            object.setFirstName(user.getFirstName());
            object.setLastName(user.getLastName());
            object.setEmail(user.getEmail());
            return userService.save(object);
        }else
            return null;
    }

    @DeleteMapping("v1.1/{id}")
    void deleteUsersByID(@PathVariable("id") Long id){
        userService.deleteById(id);
    }

}