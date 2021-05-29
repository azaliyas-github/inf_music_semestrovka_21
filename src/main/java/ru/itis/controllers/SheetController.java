package ru.itis.controllers;

import freemarker.template.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.*;
import ru.itis.model.*;
import ru.itis.services.*;
import ru.itis.utils.*;

import java.io.*;
import java.util.*;

@Controller
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
                            BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            sheetService.createSheet(sheetForm);
            return "redirect:/sheets";
        }

        return "redirect:/sheets";
    }

    @GetMapping(value = "/sheets/search", produces = "application/xml")
    public @ResponseBody String searchSheets(String query, String[] instruments) {
        Template sheetsTemplate;
        try {
            sheetsTemplate = configuration.getTemplate("sheet-list.ftlh");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        List<Sheet> sheets = sheetService.searchSheets(query, instruments);

        var attributes = new HashMap<String, Object>();
        attributes.put("sheets", sheets);
        StringWriter writer = new StringWriter();
        try {
            sheetsTemplate.process(attributes, writer);
        } catch (TemplateException | IOException e) {
            throw new IllegalStateException(e);
        }

        return writer.toString();
    }
}
