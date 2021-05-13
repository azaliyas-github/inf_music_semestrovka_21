package ru.itis.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.*;
import ru.itis.model.*;
import ru.itis.services.*;
import ru.itis.utils.*;

import java.util.*;
import java.util.stream.*;

@Controller
public class SheetController {

    @Autowired
    public SheetService sheetService;

    @Autowired
    InstrumentService instrumentService;

    @GetMapping("/sheet")
    public String getSheetsPage(Model model) {
        model.addAttribute("sheets", new ArrayList<Sheet>());
        var instruments = instrumentService.getAll().stream()
                .map(Instrument::getName)
				.sorted()
                .collect(CustomCollectors.toSortedMap(name -> name, name -> name));
        model.addAttribute("instruments", instruments);

		model.addAttribute("sheetForm", new SheetForm());
        return "sheet";
    }

    @PostMapping("/add_sheet")
    public String saveSheet(SheetForm sheetForm,
                            BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            sheetService.createSheet(sheetForm);
            return "redirect:/sheet";
        }

        return "redirect:/sheet";
    }

}
