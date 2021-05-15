package ru.itis.repository;


import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import ru.itis.model.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
