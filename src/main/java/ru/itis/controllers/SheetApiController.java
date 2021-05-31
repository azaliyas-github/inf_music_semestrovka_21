package ru.itis.controllers;

import freemarker.template.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import ru.itis.model.*;
import ru.itis.services.*;

import java.io.*;
import java.util.*;

@RestController
public class SheetApiController {
	@Autowired
	public SheetService sheetService;

	@Autowired
	InstrumentService instrumentService;

	@Autowired
	private Configuration configuration;

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
