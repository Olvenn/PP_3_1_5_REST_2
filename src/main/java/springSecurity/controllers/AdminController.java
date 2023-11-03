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

    @GetMapping("/registration")
    public String showRegistrationPage() {
        return "auth/registration";
    }
    @GetMapping()
    public String showUser() {
        return "users/user";
    }

    @GetMapping("/{id}/edit")
    public String showUpdateUserForm(@PathVariable String id) {
        return "auth/admin";
    }

    @GetMapping()
    public String showAdminPage() {
        return "auth/admin";
    }
}
