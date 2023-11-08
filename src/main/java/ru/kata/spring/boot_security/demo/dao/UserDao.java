package ru.kata.spring.boot_security.demo.dao;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Set;

@Repository
public interface UserDao extends UserDetailsService {

    void addUser(User user);


    void deleteUser(Long id);

    Set<User> getAllUsers();


    void updateUser(User user);

    User getUser(Long id);

    String getPassword(Long id);

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
