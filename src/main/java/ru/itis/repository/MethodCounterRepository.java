package ru.itis.repository;

import org.springframework.data.jpa.repository.*;
import ru.itis.model.*;

public interface MethodCounterRepository extends JpaRepository<MethodCounter, MethodCounter.Key> {
}
