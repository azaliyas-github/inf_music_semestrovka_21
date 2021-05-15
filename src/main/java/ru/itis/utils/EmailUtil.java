package ru.itis.utils;

import org.springframework.stereotype.*;


public interface EmailUtil {
    void sendMail(String to, String subject, String from, String text);
}
