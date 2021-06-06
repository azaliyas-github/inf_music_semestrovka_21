package ru.itis.controllers;

import org.springframework.core.annotation.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class DefaultExceptionHandler {
	@ExceptionHandler(value = Exception.class)
	public String exception(Model model, Exception exception) throws Exception {
		if (AnnotationUtils.findAnnotation(exception.getClass(), ResponseStatus.class) != null)
			throw exception;

		model.addAttribute("exception", exception);
		return "profile";
	}
}
