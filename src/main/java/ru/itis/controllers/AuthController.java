package ru.itis.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.*;
import ru.itis.services.*;

@Controller
public class AuthController {
    @Autowired
    public AuthService authService;

    @PostMapping("/login")
    public String login(LoginForm loginForm, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            authService.logIn(loginForm);
        }

        return "redirect:/main";
    }

    @PostMapping("/signup")
    public String signup(RegistrationForm form, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            authService.signUp(form);
        }

        return "redirect:/main";
    }
}
