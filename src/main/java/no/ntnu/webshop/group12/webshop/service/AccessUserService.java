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
import no.ntnu.webshop.group12.webshop.repository.RoleRepository;
import no.ntnu.webshop.group12.webshop.repository.UserRepository;
import no.ntnu.webshop.group12.webshop.security.AccessUserDetails;

/**
 * Service for handling user access and authentication
 */
@Service
public class AccessUserService implements UserDetailsService {

    static final Pattern usernamePattern = Pattern.compile("^([A-Za-z0-9]){2,}$");
    static final Pattern passwordPattern = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$");

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    /**
     * Tries to create a new user, returns an error message if it fails
     * 
     * @param username Users username
     * @param password Users password
     * @return Error message or null if no error
     */
    public String tryCreateNewUser(String username, String password) {
        String error = null;
        if (userExist(username)) {
            error = "User already exists";
        }
        if (error == null && !usernamePattern.matcher(username).matches()) {
            error = "Invalid username, needs to be 2 or more characters and only contain letters and numbers";
        }
        if (error == null && !passwordPattern.matcher(password).matches()) {
            error = "Invalid password needs to be 8 or more characters and contain at least one uppercase letter, one lowercase letter and one number";
        }
        if (error == null) {
            User user = new User(username, createHash(password));
            user.addRole(roleRepository.findByName("ROLE_USER"));
            userRepository.save(user);
        }
        return error;
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
        return userRepository.findByUsername(username).orElse(null);
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
        return userRepository.findByUsername(username).isPresent();
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
     * @return
     */
    public boolean updatePassword(String password, String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        return updatePassword(password, user);
    }

    /**
     * Updates the password of a user
     * 
     * @param Password The password to update
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
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return new AccessUserDetails(user.get());
        } else {
            throw new UsernameNotFoundException("User " + username + "not found");
        }
    }
}
