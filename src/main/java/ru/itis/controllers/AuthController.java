package ru.itis.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import ru.itis.exceptions.*;
import ru.itis.services.*;

@Controller
public class AuthController {
    @Autowired
    private AuthService authService;

    @GetMapping("/users/{user-id}/confirm/{confirm-code}")
    public String confirmEmail(
        @PathVariable("user-id") Long userId,
        @PathVariable("confirm-code") String confirmCode) {
        try {
            authService.confirmEmail(userId, confirmCode);
        } catch (BusinessException e) {
        	return "error";
        }
        return "confirm";
    }
}
