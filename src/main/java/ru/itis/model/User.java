package ru.itis.model;

import lombok.*;
import org.springframework.beans.factory.annotation.*;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastName;
    private String email;
    private String hashPassword;
    private String confirmCode;

    @Enumerated(value = EnumType.STRING)
    private State state;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    public enum State {
        CONFIRMED, NOT_CONFIRMED
    }

    public enum Role {
        USER, MODERATOR
    }

    public enum Status {
        ACTIVE, BANNED
    }

    public boolean isActive() {
        return this.state == State.CONFIRMED && this.status == Status.ACTIVE;
    }

    public boolean isAdmin() {
        return this.role == Role.MODERATOR;
    }

    public String getFullName() {
	    return name + " " + lastName;
    }
}
