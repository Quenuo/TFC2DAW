package com.controller;

import com.dto.BanResquestDTO;
import com.model.User;
import com.repository.UserRepository;
import com.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//para la gestion de usuarios
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public UserController(UserRepository userRepository,UserService userService){
        this.userRepository=userRepository;
        this.userService=userService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_OWNER')")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @PutMapping("/{id}/role")
    @PreAuthorize("hasRole('ROLE_OWNER')")
    public void changeUserRole(@PathVariable("id") Long userId, @RequestBody Map<String,String> userBody) {
        String role = userBody.get("role");
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setRol(role);
        userRepository.save(user);
    }


    @PostMapping("/{userId}/ban")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_OWNER')")
    public ResponseEntity<String> banUser(
            @PathVariable Long userId,
            @RequestBody BanResquestDTO banData) {

        String reason = banData.getReason();
        Integer duration = banData.getDuration();

        if (reason == null || duration == null) {
            return ResponseEntity.badRequest().body("Reason and duration are required");
        }

        userService.banUser(userId, reason, duration);
        return ResponseEntity.ok("User has been banned");
    }

    @PostMapping("/{userId}/unban")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_OWNER')")
    public ResponseEntity<String> unbanUser(@PathVariable Long userId) {
        userService.unbanUser(userId);
        return ResponseEntity.ok("User has been unbanned");
    }

    @GetMapping("/{userId}/isBanned")
    public ResponseEntity<Boolean> isBanned(@PathVariable Long userId) {
        boolean banned = userService.isBanned(userId);
        return ResponseEntity.ok(banned);
    }

}
