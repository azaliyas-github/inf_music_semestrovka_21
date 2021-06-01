package ru.itis.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.access.prepost.*;
import org.springframework.security.core.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import ru.itis.model.*;
import ru.itis.repository.*;

import java.util.*;

@Controller
@PreAuthorize("isAuthenticated()")
public class ProfileController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @GetMapping("/profile")
    public String getProfilePage(Model model, Authentication authentication) {
        Optional<User> user = userRepository.findByEmail(authentication.getName());
        if (user.isPresent()) {
			  Optional<Profile> profile = profileRepository.findByUserId(user.get().getId());
			  model.addAttribute("profile", profile.orElse(null));
			  model.addAttribute("user", user.orElse(null));
		  }

        return "profile";
    }
}
