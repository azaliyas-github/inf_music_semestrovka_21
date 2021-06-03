package ru.itis.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.access.prepost.*;
import org.springframework.web.bind.annotation.*;
import ru.itis.services.*;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("api/auth")
public class AuthApiController {
	@Autowired
	private AuthService authService;

	@PostMapping("{user-id}/ban")
	@PreAuthorize("hasAuthority('MODERATOR')")
	public void banUser(@PathVariable("user-id") Long userId) {
		authService.banUser(userId);
	}
}
