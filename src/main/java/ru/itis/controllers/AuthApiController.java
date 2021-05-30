package ru.itis.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.ui.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.*;
import ru.itis.services.*;

@RestController
@RequestMapping("api/auth")
public class AuthApiController {
    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public void signUp(@RequestBody RegistrationForm form, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors())
            authService.signUp(form);
    }
}
