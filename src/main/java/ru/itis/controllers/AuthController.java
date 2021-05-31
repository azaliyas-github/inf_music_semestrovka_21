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

@Controller
@RequestMapping("auth")
public class AuthController {
    @Autowired
    private AuthService authService;

	@PostMapping(value = "/signup")
	public String signUp(RegistrationForm form, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors())
			return "redirect:/signup";

		authService.signUp(form);
		return "redirect:/";
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
