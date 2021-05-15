package ru.itis.dto;

import lombok.*;

@Data
public class RegistrationForm {
    private String name;
    private String lastName;
    private String email;
    private String password;
}
