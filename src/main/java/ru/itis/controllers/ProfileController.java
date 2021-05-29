package ru.itis.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import ru.itis.model.*;
import ru.itis.repository.*;

import javax.annotation.security.*;
import java.util.*;

@Controller
public class ProfileController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @PermitAll
    @GetMapping("/profile")
    public String getProfilePage(Model model) {
        Optional<User> user = userRepository.findByEmail("a@a.a");
        if (user.isPresent()) {
			  Optional<Profile> profile = profileRepository.findByUserId(user.get().getId());
			  model.addAttribute("profile", profile.orElse(null));
			  model.addAttribute("user", user.orElse(null));
		  }

        return "profile";
    }
}
