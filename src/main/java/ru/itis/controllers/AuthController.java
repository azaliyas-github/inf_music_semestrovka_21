package ru.itis.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.*;
import ru.itis.exceptions.*;
import ru.itis.services.*;

import javax.annotation.security.*;
import javax.validation.*;
import java.util.*;

@Controller
@RequestMapping("auth")
public class AuthController {
    @Autowired
    private AuthService authService;

	@GetMapping("/login")
	@PermitAll
	public String getloginPage(Model model) {
		model.addAttribute("userForm", new LoginForm());
		return "login";
	}

	@GetMapping("/signup")
	@PermitAll
	public String signupPage(Model model) {
		model.addAttribute("registrationForm", new RegistrationForm());
		return "signup";
	}

	@PostMapping(value = "/signup")
	public String signUp(@Valid RegistrationForm form,
	                     BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("registrationForm", form);
			return "signup";
		}
		authService.signUp(form);
		return "redirect:/auth/login";
	}

    @GetMapping("{user-id}/confirm/{confirm-code}")
    public String confirmEmail(
            @PathVariable("user-id") Long userId,
            @PathVariable("confirm-code") String confirmCode) {
        try {
            authService.confirmEmail(userId, confirmCode);
        } catch (BusinessException e) {
            return "email-confirmation-error";
        }

        return "email-confirmed";
    }

    @GetMapping("{user-id}/resend-mail")
    public String resendConfirmMail(@PathVariable("user-id") Long userId) {
        authService.resendConfirmMail(userId);
        return "mail-resend";
    }
}
