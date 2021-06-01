package ru.itis.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import ru.itis.model.*;

import java.util.*;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
	Optional<Course> findByNameIgnoreCase(String name);
}
