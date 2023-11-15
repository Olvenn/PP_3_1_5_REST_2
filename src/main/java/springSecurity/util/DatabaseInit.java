package springSecurity.util;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import springSecurity.models.Role;
import springSecurity.models.User;
import springSecurity.service.RoleServiceImpl;
import springSecurity.service.UserServiceImp;

import javax.annotation.PostConstruct;

import java.util.Set;

@Component
public class DatabaseInit {
    private final UserServiceImp userService;
    private final RoleServiceImpl roleService;
    private final PasswordEncoder passwordEncoder;

    public DatabaseInit(UserServiceImp userService, RoleServiceImpl roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void postConstruct() {
        Role roleUser = new Role("ROLE_USER");
        Role roleAdmin = new Role("ROLE_ADMIN");
        String user1Password = passwordEncoder.encode("User");
        String admin1Password = passwordEncoder.encode("Admin");

        User admin = new User(1, "Admin", "Admin", "Admin",  "admin@gmail.com", admin1Password);
        User user = new User(2,"User", "User", "User", "user@gmail.com", user1Password);
        User test = new User(3,"Test", "Test", "Test", "user@gmail.com", user1Password);

        user.setRole(Set.of(roleUser));
        admin.setRole(Set.of(roleAdmin));
        test.setRole(Set.of(roleUser));
//
//        roleService.saveRole(roleAdmin);
//        roleService.saveRole(roleUser);

        userService.saveUser(user);
        userService.saveUser(admin);
        userService.saveUser(test);
    }
}