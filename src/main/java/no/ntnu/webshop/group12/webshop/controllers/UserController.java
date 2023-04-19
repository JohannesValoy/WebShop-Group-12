package no.ntnu.webshop.group12.webshop.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import no.ntnu.webshop.group12.webshop.models.User;
import no.ntnu.webshop.group12.webshop.models.dto.LoginDTO;
import no.ntnu.webshop.group12.webshop.models.dto.UserDTO;
import no.ntnu.webshop.group12.webshop.service.AccessUserService;
import no.ntnu.webshop.group12.webshop.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AccessUserService accessUserService;

    @GetMapping("/{id}")
    @Operation(summary = "Get user by id")
    public ResponseEntity<UserDTO> getUser(@PathVariable int id) {
        ResponseEntity<UserDTO> response = ResponseEntity.notFound().build();
        User user = userService.getUser(id);
        if (user != null) {
            response = ResponseEntity.ok(new UserDTO(user));
        }
        return response;
    }

    @GetMapping("/count")
    @Operation(summary = "Get number of users")
    public ResponseEntity<Long> getUserCount() {
        return ResponseEntity.ok(userService.getUserCount());
    }

    @PostMapping("")
    @Operation(summary = "Create a new user")
    public ResponseEntity<String> createUser(@RequestBody LoginDTO user) {
        ResponseEntity<String> response = ResponseEntity.ok().build();
        String error = accessUserService.tryCreateNewUser(user.getUsername(), user.getPassword());
        if (error != null) {
            response = ResponseEntity.badRequest().body(error);
        }
        return response;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user")
    public ResponseEntity<User> deleteUser(@PathVariable int id) {
        ResponseEntity<User> response = ResponseEntity.notFound().build();
        User user = userService.getUser(id);
        if (user != null) {
            userService.deleteUser(user);
            response = ResponseEntity.ok().build();
        }
        return response;
    }

    @GetMapping("/find/{name}")
    @Operation(summary = "Find a user by name")
    public ResponseEntity<UserDTO> findUser(@PathVariable String name) {
        ResponseEntity<UserDTO> response = ResponseEntity.notFound().build();
        Optional<User> user = userService.findUser(name);
        if (user.isPresent()) {
            response = ResponseEntity.ok(new UserDTO(user.get()));
        }
        return response;
    }
}
