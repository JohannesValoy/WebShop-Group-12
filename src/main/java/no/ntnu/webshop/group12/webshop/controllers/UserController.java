package no.ntnu.webshop.group12.webshop.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import no.ntnu.webshop.group12.webshop.models.User;
import no.ntnu.webshop.group12.webshop.models.dto.LoginDTO;
import no.ntnu.webshop.group12.webshop.service.AccessUserService;
import no.ntnu.webshop.group12.webshop.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    @Operation(summary = "Get all users")
    public ResponseEntity<Iterable<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by id")
    public ResponseEntity<User> getUser(int id) {
        ResponseEntity<User> response = ResponseEntity.notFound().build();
        Optional<User> user = userService.getUser(id);
        if (user.isPresent()) {
            response = ResponseEntity.ok(user.get());
        }
        return response;
    }

    @GetMapping("/count")
    @Operation(summary = "Get number of users")
    public ResponseEntity<Long> getUserCount() {
        return ResponseEntity.ok(userService.getUserCount());
    }

    @PostMapping("/create")
    @Operation(summary = "Create a new user")
    public ResponseEntity<User> createUser(User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a user")
    public ResponseEntity<User> deleteUser(int id) {
        ResponseEntity<User> response = ResponseEntity.notFound().build();
        Optional<User> user = userService.getUser(id);
        if (user.isPresent()) {
            userService.deleteUser(user.get());
            response = ResponseEntity.ok().build();
        }
        return response;
    }
}
