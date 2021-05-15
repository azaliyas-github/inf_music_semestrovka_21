package ru.itis.utils;

public interface MailsGenerator {
    String getMailForConfirm(String serverUrl, String code);
}
