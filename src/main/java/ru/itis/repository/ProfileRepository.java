package ru.itis.repository;

import org.springframework.data.jpa.repository.*;
import ru.itis.model.*;

import java.util.*;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByUserId(Long userId);
}
