package ru.itis.dto;

import lombok.*;

import javax.validation.constraints.*;

@Data
public class RegistrationForm {
	@NotEmpty(message = "Name should not be empty")
    private String name;
	@NotEmpty(message = "Lastname should not be empty")
    private String lastName;
	@NotEmpty(message = "Email should not be empty")
	@Email(message = "Invalid email")
    private String email;
	@NotEmpty(message = "Password should not be empty")
    private String password;
}
