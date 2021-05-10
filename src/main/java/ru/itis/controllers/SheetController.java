package ru.itis.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import ru.itis.model.*;
import ru.itis.services.*;
import ru.itis.utils.*;

import java.util.*;

@Controller
public class SheetController {
    @Autowired
    public SheetService sheetService;

    @Autowired
    private PdfUtil pdfUtil;

    @Autowired
	InstrumentService instrumentService;

    @GetMapping("/sheet")
    public String getSheetsPage(Model model) {
        model.addAttribute("sheets", new ArrayList<Sheet>());
        return "sheet";
    }
}
