package controllers;


import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import repositories.UserRepository;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/example")
public class UserController {

    private UserRepository userRepository;


    @Autowired
    public void setUserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping()
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Optional<User> getUser(@PathVariable String id){
        return userRepository.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addUser(@RequestBody User user){
        userRepository.save(user);
    }

    @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateUser(@PathVariable String id, @RequestBody User user){
        userRepository.save(user);
    }

    @DeleteMapping(path = "{id}")
    public void deleteExample(@PathVariable String id){
        userRepository.deleteById(id);
    }



}
