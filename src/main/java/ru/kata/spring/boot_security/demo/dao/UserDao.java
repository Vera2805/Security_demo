package ru.kata.spring.boot_security.demo.dao;


import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Set;

@Repository
public interface UserDao {

   Set<User> findAll();

   User getById(Long id);

   void save(User user);

   void updateUser(User user);

   void deleteById(Long id);

   User findByUsername(String username);
}