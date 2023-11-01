package ru.kata.spring.boot_security.demo.controller;

import ru.kata.spring.boot_security.demo.dao.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @RequestMapping("/user")
    public String viewUserForm(ModelMap model) {
        return "user";
    }

}





