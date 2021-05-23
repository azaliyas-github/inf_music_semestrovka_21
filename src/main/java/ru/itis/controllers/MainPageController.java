package ru.itis.controllers;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainPageController {

    @GetMapping("/main")
    public String mainPage() {
        return "index";
    }
}
