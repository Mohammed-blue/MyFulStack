package com.FullStackFinalProject.CRUDFullStack.controller;

import com.FullStackFinalProject.CRUDFullStack.exception.UserNotFoundException;
import com.FullStackFinalProject.CRUDFullStack.model.User;
import com.FullStackFinalProject.CRUDFullStack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//CrossOrigin, used to connect backend data with frontend
@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
//    Inject the user repository interface using Autowired
    @Autowired
    private UserRepository userRepository;

//    Get all the data
    @GetMapping("/users")
    List<User> getAllUsers() {
        return userRepository.findAll();
    }

//    Posting the data
    @PostMapping("/user")
    User newUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
        .orElseThrow(()-> new UserNotFoundException(id));
    }

//    Update Method
    @PutMapping("/user/{id}")
    User updateUer(@RequestBody User newUser, @PathVariable Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new UserNotFoundException(id));
    }

//    Delete Method
    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id){
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return "User with id " + id + " has been deleted successfully.";
    }
}
