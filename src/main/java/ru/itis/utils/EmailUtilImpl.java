package ru.itis.utils;

import org.springframework.stereotype.*;

@Component
public class EmailUtilImpl implements EmailUtil {
    @Override
    public void sendMail(String to, String subject, String from, String text) {
        throw new UnsupportedOperationException();
    }
}
