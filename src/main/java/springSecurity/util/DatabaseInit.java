package springSecurity.util;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import springSecurity.models.Role;
import springSecurity.models.User;
import springSecurity.service.RoleServiceImpl;
import springSecurity.service.UserServiceImp;
import java.util.HashSet;

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
        Role roleUser = new Role("USER");
        Role roleAdmin = new Role("ADMIN");
        String userPassword = passwordEncoder.encode("User");
        String adminPassword = passwordEncoder.encode("Admin");
        String testPassword = passwordEncoder.encode("Test");

        roleService.saveRole(roleAdmin);
        roleService.saveRole(roleUser);

        Set<Role> adminSet = new HashSet<>();
        Set<Role> userSet = new HashSet<>();

        adminSet.add(roleAdmin);
        adminSet.add(roleUser);
        userSet.add(roleUser);


        User admin = new User("Admin", "Admin", "Admin",  "admin@gmail.com", adminPassword);
        User user = new User("User", "User", "User", "user@gmail.com", userPassword);
        User test = new User("Test", "Test", "Test", "test@gmail.com", testPassword);

        user.setRoles(userSet);
        admin.setRoles(adminSet);

//        user.setRoles(Set.of(roleUser));
//        admin.setRoles(Set.of(roleAdmin));
//
//        roleService.saveRole(roleAdmin);
//        roleService.saveRole(roleUser);

        userService.saveUser(user);
        userService.saveUser(admin);
    }
}
