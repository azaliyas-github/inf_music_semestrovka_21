package ru.itis.services;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;
import ru.itis.aspects.*;
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

    @LogExecutionInfo
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
        sendConfirmMailTo(user);
    }

    public void confirmEmail(Long userId, String confirmCode) {
        Optional<User> userResult = usersRepository.findById(userId);
        if (userResult.isEmpty())
            throw new BusinessException("Can't find user with id " + userId);

        var user = userResult.get();
        if (!user.getConfirmCode().equals(confirmCode))
        	throw new BusinessException("Received wrong confirm code " + confirmCode
				+ " from user with id " + userId);
        if (user.getState() == User.State.CONFIRMED)
        	return;

        user.setState(User.State.CONFIRMED);
        usersRepository.save(user);
        profileRepository.save(Profile.builder()
                .userId(userId)
                .build());
    }

    public void resendConfirmMail(Long userId) {
        Optional<User> user = usersRepository.findById(userId);
        user.get().setConfirmCode(UUID.randomUUID().toString());
        usersRepository.save(user.get());

        sendConfirmMailTo(user.get());
    }

	@LogExecutionInfo
    public void banUser(Long userId) {
    	var user = usersRepository.findById(userId).get();
    	user.setStatus(User.Status.BANNED);
    	usersRepository.save(user);
    }

    private void sendConfirmMailTo(User user) {
        String confirmMail = mailsGenerator.getMailForConfirm(serverUrl,
                user.getConfirmCode(), user.getName(), user.getId());

        emailUtil.sendMail(user.getEmail(), subject, from, confirmMail);
    }
}
