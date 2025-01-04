package com.controller;

import com.model.User;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//para la gestion de usuarios
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @GetMapping
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @PutMapping("/{id}/role")
    public void changeUserRole(@PathVariable("id") Long userId, @RequestBody Map<String,String> userBody) {
        String role = userBody.get("role");
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setRol(role);
        userRepository.save(user);
    }

}
