package ru.itis.utils;

import org.springframework.stereotype.*;

@Component
public class FreemarkerMailsGeneratorImpl implements MailsGenerator {
    @Override
    public String getMailForConfirm(String serverUrl, String code) {
        throw new UnsupportedOperationException();
    }
}
