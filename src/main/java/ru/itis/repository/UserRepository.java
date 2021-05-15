package ru.itis.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import ru.itis.model.*;

import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByConfirmCode(String confirmCode);
}
