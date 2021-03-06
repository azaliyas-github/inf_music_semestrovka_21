package ru.itis.utils;

import freemarker.template.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.io.*;
import java.util.*;

@Component
public class FreemarkerMailsGeneratorImpl implements MailsGenerator {
    @Autowired
    private Configuration configuration;

    @Override
    public String getMailForConfirm(String serverUrl, String code, String username, Long userId) {
        Template confirmMailTemplate;
        try {
            confirmMailTemplate = configuration.getTemplate("mails/confirm-email.ftlh");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        // подготовили данные для шаблона
        Map<String, String> attributes = new HashMap<>();
        attributes.put("user_id", userId.toString());
        attributes.put("username", username);
        attributes.put("confirm_code", code);
        attributes.put("server_url", serverUrl);

        StringWriter writer = new StringWriter();
        try {
            // записали в StringWriter текст сообщения
            confirmMailTemplate.process(attributes, writer);
        } catch (TemplateException | IOException e) {
            throw new IllegalStateException(e);
        }
        // получили текст сообщения из шаблона
        return writer.toString();
    }
}
