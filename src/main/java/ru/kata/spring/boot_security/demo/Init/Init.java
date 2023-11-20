package ru.kata.spring.boot_security.demo.Init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class Init {
     UserService userService;
     RoleService roleService;


    @Autowired
    public Init(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;

    }

    @PostConstruct
    public void initializeData() {
        roleService.addRole (new Role("ROLE_USER"));
        Set<Role> userRole = new HashSet<>();
        userRole.add(roleService.getRoleById(2L));
        userService.addUser(new User("imya", "fam", "mail@mail.com", "q", "q", userRole));


        roleService.addRole (new Role("ROLE_ADMIN"));
        Set<Role> adminRole = new HashSet<>();
        adminRole.add(roleService.getRoleById(1L));
        userService.addUser(new User("im", "fami", "mail@mail.com", "admin", "admin", adminRole));


    }
}
