package ru.kata.spring.boot_security.demo.service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Set;

public interface UserService extends UserDetailsService {
    Set<User> getAllUsers();

    Object getUserById(Long id);

    Object getUserByUsername(String username);

    void addUser(User user);

    void updateUser(User user);

    void removeUser(Long id);

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
