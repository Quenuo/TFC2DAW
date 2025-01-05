package com.controller;

import com.model.User;
import com.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    private final UserService userService;

    @Autowired
    public ProfileController(UserService userService){
        this.userService=userService;
    }


    @GetMapping
    public User getUserProfile(@RequestParam("id") Long userId) {
        return userService.getUserById(userId);
    }

    @PutMapping
    public ResponseEntity<String> updateParkName(@RequestBody Map<String, String> data, HttpServletRequest request) {
        String userId = (String) request.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.badRequest().body("No se encontró el ID de usuario en el token.");
        }

        if (!data.containsKey("parkName")) {
            return ResponseEntity.badRequest().body("El campo 'parkName' es obligatorio.");
        }

        String parkName = data.get("parkName");

        try {
            Long id = Long.parseLong(userId);
            userService.updateParkName(id, parkName);
            return ResponseEntity.ok("Nombre del parque actualizado exitosamente.");
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("El ID de usuario del token no es válido.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/password")
    public ResponseEntity<String> changePassword(@RequestBody Map<String, String> data, HttpServletRequest request) {
        String userId = (String) request.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.badRequest().body("No se encontró el ID de usuario en el token.");
        }
        if (!data.containsKey("currentPassword") || !data.containsKey("newPassword")) {
            return ResponseEntity.badRequest().body("Los campos 'currentPassword' y 'newPassword' son obligatorios.");
        }

        String currentPassword = data.get("currentPassword");
        String newPassword = data.get("newPassword");

        try {
            Long id = Long.parseLong(userId);
            userService.updatePassword(id, currentPassword, newPassword);
            return ResponseEntity.ok("Contraseña actualizada exitosamente.");
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("El ID de usuario del token no es válido.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/reset-park")
    public ResponseEntity<String> resetPark(HttpServletRequest httpServletRequest) {
        String userId = (String) httpServletRequest.getAttribute("userId");

        if (userId == null) {
            return ResponseEntity.badRequest().body("ID de usuario no proporcionado.");
        }

        long id;
        try {
            id = Long.parseLong(userId);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("ID de usuario no válido.");
        }

        try {
            userService.resetPark(id);
            return ResponseEntity.ok("Progreso del parque reiniciado.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}