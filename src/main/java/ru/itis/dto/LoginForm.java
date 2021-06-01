package ru.itis.dto;

import lombok.*;

import javax.validation.constraints.*;

@Data
public class LoginForm {
	@NotEmpty(message = "Email should not be empty")
	@Email
    private String email;
	@NotEmpty(message = "Password should not be empty")
    private String password;
}
