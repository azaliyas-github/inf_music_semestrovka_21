package ru.itis.utils;

import org.springframework.context.annotation.*;

@Profile("dev")
public class EmailUtilFakeImpl implements EmailUtil {
    @Override
    public void sendMail(String to, String subject, String from, String text) {
        @SuppressWarnings("StringBufferReplaceableByString")
        var stringBuilder = new StringBuilder();
        stringBuilder.append("From: ").append(from).append(System.lineSeparator());
        stringBuilder.append("To: ").append(to).append(System.lineSeparator());
        stringBuilder.append("Subject: ").append(subject).append(System.lineSeparator());
        stringBuilder.append(text).append(System.lineSeparator());
        System.out.println(stringBuilder);
    }
}
