package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Controller
@Valid
@RequestMapping("/admin")
public class AdminController {

    private final RoleService roleService;
    private final UserService userService;
    private static final String REDIRECT = "redirect:/admin";


    @Autowired
    public AdminController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping(value = "")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping(value = "/{id}")
    public String getUserById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user";
    }

    @GetMapping(value = "/new")
    public String addUser(Model model) {
        model.addAttribute("user", new  User());
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "make";
    }

    @PostMapping(value = "/new")
    public String add(@Valid @ModelAttribute("user") User user, BindingResult bindingResult
            , Model model, @RequestParam(value = "ids", required = false) List<Long> ids) {
        if (bindingResult.hasErrors()) {

            model.addAttribute("allRoles", roleService.getAllRoles());
            Set<Role> assignedRole = roleService.findAllRoleId(ids);
            user.setRoles(assignedRole);

            return "make";
        }

        if ( ids == null || ids.isEmpty()) {
            bindingResult.rejectValue("roles", "error.roles.empty", "No roles selected");
            model.addAttribute("allRoles", roleService.getAllRoles());
            Set<Role> assignedRole = roleService.findAllRoleId(ids);
            user.setRoles(assignedRole);
            return "make";
        }

        try {

            Set<Role> assignedRole = roleService.findAllRoleId(ids);
            user.setRoles(assignedRole);
            userService.addUser(user);
            return REDIRECT;
        } catch (DataIntegrityViolationException e) {
            bindingResult.rejectValue("username", "duplicate", "This is username is already taken");
            model.addAttribute("allRoles", roleService.getAllRoles());
            Set<Role> assignedRole = roleService.findAllRoleId(ids);
            user.setRoles(assignedRole);
            return "make";
        }

    }

    @DeleteMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.removeUser(id);
        return REDIRECT;
    }

    @GetMapping(value = "/edit/{id}")
    public String updateUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "edit";
    }

    @PatchMapping(value = "/edit")
    public String update(@Valid @ModelAttribute("user") User user, BindingResult bindingResult
            , Model model, @RequestParam(value = "ids", required = false) List<Long> ids) {
        if (bindingResult.hasErrors()) {

            model.addAttribute("allRoles", roleService.getAllRoles());
            Set<Role> assignedRole = roleService.findAllRoleId(ids);
            user.setRoles(assignedRole);

            return "edit";
        }

        if (ids == null || ids.isEmpty()) {
            bindingResult.rejectValue("roles", "errors","No roles selected");
            model.addAttribute("allRoles", roleService.getAllRoles());
            Set<Role> assignedRole = roleService.findAllRoleId(ids);
            user.setRoles(assignedRole);
            return "edit";
        }

        try {
            Set<Role> assignedRole = roleService.findAllRoleId(ids);
            user.setRoles(assignedRole);
            userService.updateUser(user);
            return REDIRECT;
        } catch (DataIntegrityViolationException e) {
            bindingResult.


            rejectValue("username", "duplicate", "This is username is already taken");
            model.addAttribute("allRoles", roleService.getAllRoles());
            Set<Role> assignedRole = roleService.findAllRoleId(ids);
            user.setRoles(assignedRole);
            return "edit";
        }
    }
}