package no.ntnu.webshop.group12.webshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import no.ntnu.webshop.group12.webshop.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
}
