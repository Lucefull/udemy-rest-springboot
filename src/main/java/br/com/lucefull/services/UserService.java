package br.com.lucefull.services;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.lucefull.repositories.UserRepository;

public class UserService implements UserDetailsService {
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    UserRepository userRepository;

    public UserService(UserRepository repository) {
        this.userRepository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Finding one user by nem " + username + "!");
        var user = userRepository.findByUserName(username);

        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("Username " + username + " not found!");
        }
    }
}
