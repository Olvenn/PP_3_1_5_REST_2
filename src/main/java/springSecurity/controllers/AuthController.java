package springSecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springSecurity.models.User;
import springSecurity.service.RegistrationService;
import springSecurity.service.RoleServiceImpl;
import springSecurity.service.UserServiceImp;
import springSecurity.util.UserValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final RegistrationService registrationService;
    private final UserValidator personValidator;
    private final RoleServiceImpl roleService;
    private UserServiceImp userService;

    @Autowired
    public AuthController(RegistrationService registrationService,
                          UserValidator personValidator,
                          RoleServiceImpl roleService,
                          UserServiceImp userService) {
        this.registrationService = registrationService;
        this.personValidator = personValidator;
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "auth/login";
    }

//    @GetMapping("/registration")
//    public String showRegistrationPage(Model model) {
//        model.addAttribute("roleDefault", "ROLE_ADMIN");
//        model.addAttribute("roles", roleService.getRoles());
//        model.addAttribute("admin", userService.findByUsername("Admin"));
//        model.addAttribute("user", new User());
//        return "auth/registration";
//    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("user") @Valid User user,
                                      BindingResult bindingResult) {
        personValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors())
            return "/auth/registration";

        registrationService.register(user);

        return "redirect:/admin";
    }
}
