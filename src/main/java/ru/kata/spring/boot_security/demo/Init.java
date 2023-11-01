package ru.kata.spring.boot_security.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;

import java.util.Collections;
import java.util.Set;

@Component
public class Init {
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public Init(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    @Transactional
    public void initializeData() {
        Role userRole = new Role("ROLE_USER");
        roleService.addRole(userRole);
        User user = new User("imya", "fam", "mail@mail.com", "q", passwordEncoder.encode("q"));
        user.setRoles(Collections.singleton(userRole));
        userService.addUser(user);

        Role adminRole = new Role("ROLE_ADMIN");
        roleService.addRole(adminRole);
        User admin = new User("im", "fami", "mail@mail.com", "admin", passwordEncoder.encode("admin"));
        admin.setRoles(Set.of(adminRole, userRole));
        userService.addUser(admin);

    }
}
