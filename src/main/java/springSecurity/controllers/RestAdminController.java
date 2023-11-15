package springSecurity.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springSecurity.models.Role;
import springSecurity.models.User;
import springSecurity.service.RoleServiceImpl;
import springSecurity.service.UserServiceImp;

import java.security.Principal;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestAdminController {

    private UserServiceImp userService;
    private final RoleServiceImpl roleService;

    public RestAdminController(UserServiceImp userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

//    @GetMapping("/all")
//    public List<User> showUsers() {
//
//        List<User> users = userService.findAllUsers();
//
//        return users;
//    }

    @GetMapping()
    public ResponseEntity<List<User>> showUsers() {
        List<User> users = userService.findAllUsers();
        System.out.println(users);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

//    @GetMapping("/users")
//    public ResponseEntity<List<ResponseEntity>> showAllUsers(Principal principal) {
//        User user = userService.findByUsername(principal.getName());
//        List<User> users = userService.findAllUsers();
//        List<Role> roles = roleService.getRoles();
//
//        ResponseEntity userEntity = new ResponseEntity<>(user, HttpStatus.OK);
//        ResponseEntity usersEntity = new ResponseEntity<>(users, HttpStatus.OK);
//        ResponseEntity rolesEntity = new ResponseEntity<>(roles, HttpStatus.OK);
//        List<ResponseEntity> entities = Arrays.asList(userEntity, usersEntity, rolesEntity);
//
//        return new ResponseEntity<>(entities, HttpStatus.OK);
//    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getOneUser(@PathVariable("id") Long id) {
        User user = userService.findOneUser(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> editUser(@RequestBody User user) {
        userService.updateUser(user.getId(), user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
//
//    @DeleteMapping("/del/{id}")
//    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
//        userService.deleteUser(id);
//        return ResponseEntity.ok().build();
//    }

//    @DeleteMapping("/del/{id}")
//    public ResponseEntity<User> restDelete(@RequestBody User user, Principal principal) {
//        if (!(principal.getName().equals(user.getUsername()))) {
//            userService.deleteUser(user.getId());
//        }
//        return ResponseEntity.ok(user);
//    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) throws Exception {
        User user = userService.findOneUser(id);
        if(user == null) {
            throw new Exception("There is no user with id = " + id + " in database.");
        }

        userService.deleteUser(id);
        return ResponseEntity.ok("User with id = " + id + " was deleted.");
    }
}

