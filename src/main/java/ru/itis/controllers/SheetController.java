package ru.itis.controllers;

import freemarker.template.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.access.prepost.*;
import org.springframework.security.core.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.*;
import ru.itis.model.*;
import ru.itis.security.details.*;
import ru.itis.services.*;
import ru.itis.utils.*;

import java.io.*;
import java.util.*;

@Controller
@PreAuthorize("isAuthenticated()")
public class SheetController {
    @Autowired
    public SheetService sheetService;

    @Autowired
    InstrumentService instrumentService;

    @Autowired
    private Configuration configuration;

    @GetMapping("/sheets")
    public String getSheetsPage(Model model) {
        model.addAttribute("sheets", sheetService.getAllSheets());
        var instruments = instrumentService.getAll().stream()
                .map(Instrument::getName)
				.sorted()
                .collect(CustomCollectors.toSortedMap(name -> name, name -> name));
        model.addAttribute("instruments", instruments);

		model.addAttribute("sheetForm", new SheetForm());
        return "sheets";
    }

    @PostMapping("/add_sheet")
    public String saveSheet(SheetForm sheetForm,
                            BindingResult bindingResult, Model model,
	         Authentication authentication) {
        if (!bindingResult.hasErrors()) {
            sheetService.createSheet(sheetForm, authentication.getName());
            return "redirect:/sheets";
        }

        return "redirect:/sheets";
    }
}
