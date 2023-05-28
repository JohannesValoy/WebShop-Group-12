package no.ntnu.webshop.group12.webshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import no.ntnu.webshop.group12.webshop.exception.NotFoundException;
import no.ntnu.webshop.group12.webshop.models.User;
import no.ntnu.webshop.group12.webshop.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUser(int id) {
        return userRepository.findById(id);
    }

    public User getUser(String name) {
        Optional<User> user = userRepository.findByUsernameIgnoreCase(name);
        if(!user.isPresent()) {
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
        if(!user.isPresent()) {
            throw new NotFoundException("User not found");
        }
        userRepository.delete(user.get());
    }
}
