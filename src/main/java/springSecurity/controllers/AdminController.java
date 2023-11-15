package springSecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springSecurity.models.User;
import springSecurity.service.RoleServiceImpl;
import springSecurity.service.UserServiceImp;
import springSecurity.util.UserValidator;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/admin")

public class AdminController {
    private UserServiceImp userService;
    private final UserValidator userValidator;
    private final UserValidator UsersDetailsService;
    private final RoleServiceImpl roleService;


    @Autowired
    public AdminController(UserServiceImp userService, UserValidator userValidator, UserValidator usersDetailsService, RoleServiceImpl roleService) {

        this.userService = userService;
        this.userValidator = userValidator;
        this.UsersDetailsService = usersDetailsService;
        this.roleService = roleService;
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") long id, ModelMap model) {
        model.addAttribute("user", userService.findOneUser(id));
        return "users/user";
    }


//    @GetMapping("/user")
//    public String showUser(Model model, Principal principal) {
//        model.addAttribute("user", userService.findByUsername(principal.getName()));
//        return "users/user";
//    }

    @GetMapping("/{id}/edit")
    public String showUpdateUserForm(Model model, Principal principal, @PathVariable("id") long id) {
        model.addAttribute("user", userService.findByUsername(principal.getName()));
        model.addAttribute("user", userService.findOneUser(id));
        return "users/editUser";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                             @PathVariable("id") long id) {
        if (bindingResult.hasErrors())
            return "users/editUser";

        userService.updateUser(id, user);
        return "redirect:/user";
    }

//    @DeleteMapping("/{id}")
//    public String deleteUser(@PathVariable("id") long id) {
//        userService.deleteUser(id);
//        return "redirect:/user";
//    }


    @GetMapping("/user")
    public String showAdmin(Principal principal) {
        return "users/user";
    }


    @GetMapping()
    public String showAdminPage(ModelMap model, Principal principal) {
//        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("admin", userService.findByUsername(principal.getName()));
//        model.addAttribute("roles", roleService.getRoles());
        return "auth/admin";
    }
}
