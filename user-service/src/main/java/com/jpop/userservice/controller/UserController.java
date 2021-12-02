package com.jpop.userservice.controller;

import com.jpop.userservice.model.User;
import com.jpop.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("users")
    List<User> getAllUsers(){
        return userService.findAll();
    }

    @PostMapping("users")
    User addUser(@RequestBody User user){
         return userService.save(user);
    }

    @PutMapping("users/{id}")
    User updateBooks(@RequestBody User user,@PathVariable("id") Long id){
        Optional<User> entity =  userService.findById(id);
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

    @DeleteMapping("users/{id}")
    void deleteUsersByID(@PathVariable("id") Long id){
        userService.deleteById(id);
    }

}