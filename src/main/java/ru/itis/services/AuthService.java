package ru.itis.services;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;
import ru.itis.dto.*;
import ru.itis.model.*;
import ru.itis.repository.*;
import ru.itis.utils.*;

import java.util.*;

@Service
public class AuthService {
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
                .hashedPassword(passwordEncoder.encode(form.getPassword()))
                .state(User.State.NOT_CONFIRMED)
                .role(User.Role.USER)
                .status(User.Status.ACTIVE)
                .confirmCode(UUID.randomUUID().toString())
                .build();

        usersRepository.save(user);
        String confirmMail = mailsGenerator.getMailForConfirm(serverUrl, user.getConfirmCode());
        emailUtil.sendMail(user.getEmail(), subject, from, confirmMail);
    }

    public void logIn(LoginForm form) {

    }
}
