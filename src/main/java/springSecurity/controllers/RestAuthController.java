package springSecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import springSecurity.models.User;
import springSecurity.service.RoleServiceImpl;
import springSecurity.service.UserServiceImp;

@Controller
@RequestMapping("/api")
public class RestAuthController {
    private UserServiceImp userService;
    private final RoleServiceImpl roleService;

    @Autowired
    public RestAuthController(UserServiceImp userService, RoleServiceImpl roleService) {

        this.userService = userService;
        this.roleService = roleService;
    }
    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> saveUserApi(@RequestBody User user) {
        System.out.println(user);
        userService.saveUser(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
