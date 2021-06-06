package ru.itis.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.access.prepost.*;
import org.springframework.security.core.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.*;
import ru.itis.model.*;
import ru.itis.services.*;
import ru.itis.utils.*;

import java.util.*;

@Controller
@PreAuthorize("isAuthenticated()")
public class SheetController {
    @Autowired
    public SheetService sheetService;

    @Autowired
    InstrumentService instrumentService;

	@GetMapping("/sheets")
    public String getSheetsPage(Model model, @RequestParam(required = false) String instrumentName) {
	    List<Sheet> sheets = instrumentName != null
	        ? sheetService.filterSheets(instrumentName)
		    : sheetService.getAllSheets();
        if (instrumentName != null && sheets == null) {
            throw new IllegalArgumentException("Instrument " + instrumentName + " not found");
        }
		model.addAttribute("sheets", sheets);

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
