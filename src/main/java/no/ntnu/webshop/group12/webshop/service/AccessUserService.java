package no.ntnu.webshop.group12.webshop.service;

import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import no.ntnu.webshop.group12.webshop.models.User;
import no.ntnu.webshop.group12.webshop.repository.CartRepository;
import no.ntnu.webshop.group12.webshop.repository.RoleRepository;
import no.ntnu.webshop.group12.webshop.repository.UserRepository;
import no.ntnu.webshop.group12.webshop.security.AccessUserDetails;

/**
 * Service for handling user access and authentication
 */
@Service
public class AccessUserService implements UserDetailsService {

    // Regex for username and password validation

    // Username needs to be 2 or more characters and only contain letters and
    // numbers
    static final Pattern usernamePattern = Pattern.compile("^([A-Za-z0-9]){2,}$");

    // Password needs to be 8 or more characters and contain at least one uppercase
    // letter, one lowercase letter and one number
    static final Pattern passwordPattern = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$");

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    CartRepository cartRepository;

    /**
     * Tries to create a new user, returns an error message if it fails
     * 
     * @param username Users username
     * @param password Users password
     * @return Error message or null if no error
     */
    public User tryCreateNewUser(String username, String password) {
        if (userExist(username)) {
            throw new IllegalArgumentException("User already exists");
        }
        if (!usernamePattern.matcher(username).matches()) {
            throw new IllegalArgumentException(
                    "Invalid username, needs to be 2 or more characters and only contain letters and numbers");
        }
        if (!passwordPattern.matcher(password).matches()) {
            throw new IllegalArgumentException(
                    "Invalid password needs to be 8 or more characters and contain at least one uppercase letter, one lowercase letter and one number");
        }
        User user = new User(username, createHash(password));
        user.addRole(roleRepository.findByName("ROLE_USER"));
        // Adds the admin role to a user called admin when the user is created
        if (user.getUsername().equalsIgnoreCase("admin")) {
            user.addRole(roleRepository.findByName("ROLE_ADMIN"));
        }
        cartRepository.save(user.getCart());
        userRepository.save(user);
        return user;
    }

    /**
     * Fetches the current user logged in
     * 
     * @return The current user logged in
     */
    public User getSessionUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String username = authentication.getName();
        return userRepository.findByUsernameIgnoreCase(username).orElse(null);
    }

    /**
     * Creates a hash of the password
     * 
     * @return the hash of the password
     */
    private String createHash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * Checks if a user exists
     * 
     * @param username The username to check
     * @return True if the user exists
     */
    private boolean userExist(String username) {
        boolean returnBool = true;
        try {
            loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            returnBool = false;
        }
        return returnBool;
    }

    /**
     * Allows for updating the password of the current user
     * 
     * @param password The password to check
     * @return True if the password is correct
     */
    public boolean updatePassword(String password) {
        User user = getSessionUser();
        return updatePassword(password, user);
    }

    /**
     * Allows for updating the password of a user
     * Should only be used by admins
     * 
     * @param password The password to check
     * @param username The username of the user to update
     * @return True if the password is correct
     */
    public boolean updatePassword(String password, String username) {
        User user = userRepository.findByUsernameIgnoreCase(username).orElse(null);
        return updatePassword(password, user);
    }

    /**
     * Updates the password of a user
     * 
     * @param password The password to update
     * @param user     The user to update
     * @return True if the password was updated
     */
    private boolean updatePassword(String password, User user) {
        boolean returnValue = false;
        if (user != null && passwordPattern.matcher(password).matches()) {
            user.setPassword(createHash(password));
            userRepository.save(user);
            returnValue = true;
        }
        return returnValue;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsernameIgnoreCase(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User " + username + " not found");
        }
        return new AccessUserDetails(user.get());
    }

    public void deleteCurrentUser() {
        User user = getSessionUser();
        userRepository.delete(user);
    }
}
