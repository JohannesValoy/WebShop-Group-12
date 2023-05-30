package no.ntnu.webshop.group12.webshop.controllers.api;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import no.ntnu.webshop.group12.webshop.exception.NotFoundException;
import no.ntnu.webshop.group12.webshop.models.User;
import no.ntnu.webshop.group12.webshop.models.dto.LoginDTO;
import no.ntnu.webshop.group12.webshop.service.AccessUserService;
import no.ntnu.webshop.group12.webshop.service.UserService;

/**
 * Rest controller for user
 * 
 * @version 1.0
 * @since 2023-04-25
 */
@RestController
@Tag(name = "User", description = "User API")
@RequestMapping("/api/users")
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
     * @throws NotFoundException if it did not find the user
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get user by id")
    public User getUser(@PathVariable int id) {
        Optional<User> user = userService.getUser(id);
        if (!user.isPresent()) {
            throw new NotFoundException("User not found");
        }
        return user.get();
    }

    /**
     * Get current user
     * @return current user
     */
    @GetMapping("/me")
    @Operation(summary = "Get current user")
    public User getCurrentUser() {
        User user = accessUserService.getSessionUser();
        if (user == null) {
            throw new NotFoundException("User not found");
        }
        return user;
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
    @PostMapping
    @Operation(summary = "Create a new user")
    public User createUser(@RequestBody LoginDTO user) {
        return accessUserService.tryCreateNewUser(user.getUsername(), user.getPassword());
    }

    /**
     * Delete a user
     * 
     * @param id user id
     * @return deleted user
     * @throws NotFoundException if it did not find the user
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }

    @DeleteMapping("/me")
    @Operation(summary = "Delete current user")
    public void deleteCurrentUser() {
        userService.deleteCurrentUser();
    }

    /**
     * Get users by filter
     * 
     * @param pageable
     * @param predicate
     * @return list of users
     */
    @GetMapping
    @Operation(summary = "Get users by filter")
    public List<User> getUsersByFilter(
            @ParameterObject @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
            @ParameterObject @QuerydslPredicate(root = User.class) Predicate predicate) {
        return userService.getUsersByFilter(predicate, pageable);
    }


}
