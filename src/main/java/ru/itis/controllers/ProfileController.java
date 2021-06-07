package ru.itis.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.access.prepost.*;
import org.springframework.security.core.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import ru.itis.model.*;
import ru.itis.repository.*;
import ru.itis.services.*;

import java.util.*;

@Controller
@PreAuthorize("isAuthenticated()")
public class ProfileController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ChatService chatService;

    @GetMapping("/profile")
    public String getProfilePage(Model model, Authentication authentication) {
        Optional<User> userResult = userRepository.findByEmail(authentication.getName());
        if (userResult.isPresent()) {
        	var user = userResult.get();
			  Optional<Profile> profile = profileRepository.findByUserId(user.getId());
			  model.addAttribute("profile", profile.orElse(null));
			  model.addAttribute("user", user);
		  }

        return "profile";
    }
}
