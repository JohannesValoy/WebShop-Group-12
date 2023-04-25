package no.ntnu.webshop.group12.webshop.controllers;

import java.util.List;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import no.ntnu.webshop.group12.webshop.models.User;
import no.ntnu.webshop.group12.webshop.models.dto.LoginDTO;
import no.ntnu.webshop.group12.webshop.service.AccessUserService;
import no.ntnu.webshop.group12.webshop.service.UserService;

@RestController
@Tag(name = "User", description = "User API")
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AccessUserService accessUserService;

    /**
     * Get user by id
     * 
     * @param id user id
     * @return user if found, otherwise 404
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get user by id")
    public User getUser(@PathVariable int id) {
        return userService.getUser(id);
    }

    /**
     * Get the number of users
     * 
     * @return number of users
     */
    @GetMapping("/count")
    @Operation(summary = "Get number of users")
    public ResponseEntity<Long> getUserCount() {
        return ResponseEntity.ok(userService.getUserCount());
    }

    /**
     * Creates a new user
     * 
     * @param user user to create
     * @return 200 if successful, otherwise 400
     */
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

    @GetMapping("/filter")
    @Operation(summary = "Get categories by filter")
    public List<User> getUsersByFilter(
            @ParameterObject @PageableDefault(size = 5, direction = Sort.Direction.ASC) Pageable pageable,
            @ParameterObject @QuerydslPredicate(root = User.class) Predicate predicate) {
        return userService.getUsersByFilter(predicate, pageable);
    }
}
