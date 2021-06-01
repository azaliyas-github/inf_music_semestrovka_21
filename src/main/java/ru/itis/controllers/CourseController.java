package ru.itis.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import ru.itis.model.*;
import ru.itis.services.*;

import java.util.*;

@Controller
@RequestMapping("courses")
public class CourseController {
	@Autowired
	private CourseService courseService;

	@GetMapping("{name}")
	public String coursePage(@PathVariable("name") String courseName,
	                         Model model) {
		Optional<Course> course = courseService.getCourseByName(courseName);
		if (course.isEmpty()) {
			throw new IllegalArgumentException("Такого курса не существует");
		}
		model.addAttribute("course", course.get());
		return "course";
	}
}
