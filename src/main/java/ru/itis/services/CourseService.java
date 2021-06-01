package ru.itis.services;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import ru.itis.model.*;
import ru.itis.repository.*;

import java.util.*;

@Service
public class CourseService {
	@Autowired
	private CourseRepository courseRepository;

	public Optional<Course> getCourseByName(String name) {
		return courseRepository.findByNameIgnoreCase(name);
	}

	public void save(Course course) {
		courseRepository.save(course);
	}
}
