package com.example.airbnb.controllers;

import java.util.List;

import com.example.airbnb.entities.User;
import com.example.airbnb.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController
 */

@RestController
@RequestMapping(path="/api")
public class UserController {

    @Autowired
    UserRepository userRepository;

    //http://localhost:8080/api/users
    @GetMapping(path="/users", produces="application/json")
    public List<User> displayUsers() {
        return userRepository.getAllUsers();
    }

    //search by email
    //http://localhost:8080/api/users_emails?email=jane_doe@gmail.com
    @GetMapping(path="/users_emails", produces="application/json")
    public List<User> displayUsersByEmail(@RequestParam String email){
        return userRepository.getUsersByEmail(email);
    }

    //add user
    //http://localhost:8080/api/create_user
    @GetMapping(path="/create_user")
    public void addUser(){
        userRepository.addUser("Spy", "Fox", "spy.fox@agent.com", "0123456789");
    }

    //update user
    //http://localhost:8080/api/update_user
    @GetMapping(path="/update_user")
    public void updateUser(){
        userRepository.updateUser("spy.fox@agent.com", "spy_fox@agent.com");
    }

    //delete user
    //http://localhost:8080/api/delete_user
    @GetMapping(path="/delete_user")
    public void deleteUser(){
        userRepository.deleteUser(0);
    }

    //create user: name, email, phone
    //http://localhost:8080/api/users
    @PostMapping(value="/users", produces="application/json")
    //payload = RequestBody
    public User createUser(@RequestBody User user) {
        userRepository.createUser(user);
        return user;
    }

    //update user by id: name, email, phone
    //http://localhost:8080/api/users/12
    @PostMapping(value="/users/{id}", produces="application/json")
    //payload = RequestBody
    public User updateUserById(@RequestBody User user, @PathVariable("id") int id) {
        userRepository.updateUserById(user, id);
        return user;
    }

}