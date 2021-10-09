package jm.security.example.controller;

import jm.security.example.model.Role;
import jm.security.example.model.User;
import jm.security.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("users", userService.index());
        model.addAttribute("user", new User());
        return "admin/index";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "admin/new";
    }

    @PostMapping
    public String createUser(@RequestParam(value = "ROLE_USER_checkbox", required = false) String ROLE_USER_checkbox,
                             @RequestParam(value = "ROLE_ADMIN_checkbox", required = false) String ROLE_ADMIN_checkbox,
                             @ModelAttribute("user") User user) {
        Set<Role> roles = new HashSet<>();
        if (ROLE_ADMIN_checkbox != null) {
            roles.add(userService.getRoleById(1));
            user.setRoles(roles);
        }
        if (ROLE_USER_checkbox != null) {
            roles.add(userService.getRoleById(2));
            user.setRoles(roles);
        }
        userService.create(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.show(id));
        return "admin/edit";
    }

    @PatchMapping("/{id}")
    public String update(@RequestParam(value = "ROLE_USER_checkbox", required = false) String ROLE_USER_checkbox,
                         @RequestParam(value = "ROLE_ADMIN_checkbox", required = false) String ROLE_ADMIN_checkbox,
                         @ModelAttribute("user") User user) {
        Set<Role> roles = new HashSet<>();
        if (ROLE_ADMIN_checkbox != null) {
            roles.add(userService.getRoleById(1));
            user.setRoles(roles);
        }
        if (ROLE_USER_checkbox != null) {
            roles.add(userService.getRoleById(2));
            user.setRoles(roles);
        }
        userService.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
