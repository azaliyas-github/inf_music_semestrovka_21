package ru.itis.controllers;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import ru.itis.exceptions.*;

@Controller
public class MainPageController {

    @GetMapping("/")
    public String mainPage() {
        return "main";
    }

    @GetMapping("/tester")
    public String test() {
    	throw new BusinessException("8");
	 }
}
