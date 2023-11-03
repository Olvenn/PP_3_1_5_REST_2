package springSecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springSecurity.service.UserServiceImp;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UsersController {

    @GetMapping()
    public String showUser() {
        return "users/user";
    }
}
