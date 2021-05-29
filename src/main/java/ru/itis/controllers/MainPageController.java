package ru.itis.controllers;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainPageController {

    @GetMapping("/")
    public String mainPage() {
        return "main";
    }
}
