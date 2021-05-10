package ru.itis.services;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import ru.itis.model.*;
import ru.itis.repository.*;
import org.icepdf.core.pobjects.Document;

import java.util.*;
import java.util.stream.*;

@Service
public class InstrumentService {
    @Autowired
    private InstrumentRepository instrumentRepository;

    public List<Instrument> getInstrumentsByName(String[] instrumentNames) {
        var instrumentNamesSet = Arrays.stream(instrumentNames).collect(Collectors.toSet());
		var document = new Document();
        return instrumentRepository.findAll().stream()
                .filter(instrument -> instrumentNamesSet.contains(instrument.getName()))
                .collect(Collectors.toList());
    }
}
