package jm.security.example.config;

import jm.security.example.model.Role;
import jm.security.example.model.User;
import jm.security.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class DaoInit {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    private void createUsers(){
        userService.saveRole(new Role("ROLE_ADMIN"));
        userService.saveRole(new Role("ROLE_USER"));
        Set<Role> rolesAdmin = new HashSet<>(userService.getAllRoles());
        userService.create(new User("admin", "admin", 99, "admin@admin.com", rolesAdmin));
        Set<Role> rolesUser = new HashSet<>();
        rolesUser.add(userService.getRoleById(2));
        userService.create(new User("user", "user", 99, "user@user.com", rolesUser));
    }
}
