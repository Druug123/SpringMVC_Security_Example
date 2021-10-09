package jm.security.example.controller;


import jm.security.example.model.Role;
import jm.security.example.model.User;
import jm.security.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class HelloController {

    private final UserService userService;

    @Autowired
    public HelloController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String welcome() {
        return "index";
    }

    @GetMapping("/registration")
    public String newUser(@ModelAttribute("user") User user) {
        return "/registration";
    }

    @PostMapping("/registration")
    public String create(@ModelAttribute("user") User user) {
        Set<Role> roles = new HashSet<>();
        roles.add(userService.getRoleById(2));
        user.setRoles(roles);
        userService.create(user);
        return "redirect:/login";
    }
}
