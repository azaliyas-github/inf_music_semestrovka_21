package ru.itis.services;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.context.*;
import org.springframework.security.crypto.password.*;
import org.springframework.security.web.authentication.*;
import org.springframework.security.web.context.*;
import org.springframework.stereotype.*;
import ru.itis.dto.*;
import ru.itis.exceptions.*;
import ru.itis.model.*;
import ru.itis.repository.*;
import ru.itis.utils.*;

import java.util.*;

@Service
public class AuthService {
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private EmailUtil emailUtil;

    @Autowired
    private MailsGenerator mailsGenerator;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository usersRepository;

    @Value("${spring.mail.properties.server.url}")
    private String serverUrl;

    @Value("${spring.mail.username}")
    private String from;

    @Value("${spring.mail.properties.mail.subject}")
    private String subject;

    public void signUp(RegistrationForm form) {
        User user = User.builder()
                .name(form.getName())
                .lastName(form.getLastName())
                .email(form.getEmail())
                .hashPassword(passwordEncoder.encode(form.getPassword()))
                .state(User.State.NOT_CONFIRMED)
                .role(User.Role.USER)
                .status(User.Status.ACTIVE)
                .confirmCode(UUID.randomUUID().toString())
                .build();

        usersRepository.save(user);
        String confirmMail = mailsGenerator.getMailForConfirm(serverUrl, user.getConfirmCode());
        emailUtil.sendMail(user.getEmail(), subject, from, confirmMail);
    }

    public boolean confirmEmail(String confirmCode) {
        Optional<User> user = usersRepository.findByConfirmCode(confirmCode);
        if (user.isEmpty())
            throw new BusinessException("Wrong confirm code " + confirmCode);

        profileRepository.save(Profile.builder()
                .userId(user.get().getId())
                .build());
        return true;
    }
}
