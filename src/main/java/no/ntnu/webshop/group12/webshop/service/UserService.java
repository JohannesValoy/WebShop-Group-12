package no.ntnu.webshop.group12.webshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import no.ntnu.webshop.group12.webshop.exception.NotFoundException;
import no.ntnu.webshop.group12.webshop.models.User;
import no.ntnu.webshop.group12.webshop.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccessUserService accessUserService;

    public Optional<User> getUser(int id) {
        return userRepository.findById(id);
    }

    public User getUser(String name) {
        Optional<User> user = userRepository.findByUsernameIgnoreCase(name);
        if(user.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        return user.get();
    }

    public long getUserCount() {
        return userRepository.count();
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public Optional<User> findUser(String name) {
        return userRepository.findByUsernameIgnoreCase(name);
    }

    public List<User> getUsersByFilter(Predicate predicate, Pageable pageable) {
        return userRepository.findAll(predicate, pageable).getContent();
    }

    public void deleteUser(int id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        deleteUser(user.get());
    }

    /**
     * Deletes the current user and logs out to invalidate the session
     * @param request The request to logout from
     * @throws ServletException If the logout fails
     */
    public void deleteCurrentUser(HttpServletRequest request) throws ServletException {
        deleteUser(accessUserService.getSessionUser());
        request.logout();
    }
}
