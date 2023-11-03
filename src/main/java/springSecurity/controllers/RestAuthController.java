package springSecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import springSecurity.models.Role;
import springSecurity.models.User;
import springSecurity.service.RoleServiceImpl;
import springSecurity.service.UserServiceImp;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

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
        userService.saveUser(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

//    @GetMapping("/registration")
//    public ResponseEntity<User> showUserApi(Principal principal) {
//
//        return new ResponseEntity<>(userService.findByUsername(principal.getName()), HttpStatus.OK);
//    }

    @GetMapping("/registration")
    public ResponseEntity<List<ResponseEntity>> getUsersInfo(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        List<User> users = userService.findAllUsers();
        List<Role> roles = roleService.getRoles();

        ResponseEntity userEntity = new ResponseEntity<>(user, HttpStatus.OK);
        ResponseEntity usersEntity = new ResponseEntity<>(users, HttpStatus.OK);
        ResponseEntity rolesEntity = new ResponseEntity<>(roles, HttpStatus.OK);
        List<ResponseEntity> entities = Arrays.asList(userEntity, usersEntity, rolesEntity);

        return new ResponseEntity<>(entities, HttpStatus.OK);
    }
}
