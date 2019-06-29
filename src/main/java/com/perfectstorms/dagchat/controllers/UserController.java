package com.perfectstorms.dagchat.controllers;

import com.perfectstorms.dagchat.entities.User;
import com.perfectstorms.dagchat.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @GetMapping(value = "/users/{name}")
    public User getOneByName(@PathVariable String name) {
        return userRepository.getOneByName(name);
    }

    @GetMapping(value = "/user/{id}")
    public User getOneById(@PathVariable Long id) {
        return userRepository.getOneById(id);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity addUser(@RequestBody User user) {
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/users", method = RequestMethod.DELETE)
    public ResponseEntity deleteUserById(Long id) {
        userRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
