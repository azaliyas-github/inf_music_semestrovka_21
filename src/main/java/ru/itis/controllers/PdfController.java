package ru.itis.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.*;
import ru.itis.repository.*;
import ru.itis.utils.*;

@Controller
@RequestMapping("api/pdf/")
public class PdfController {
    @Autowired
    private PdfRepository repository;

    @GetMapping("{fileName}")
    public StreamingResponseBody download(@PathVariable("fileName") String fileName) {
        var file = repository.getRaw(fileName);
        return StreamUtils.returnBytes(file);
    }
}
