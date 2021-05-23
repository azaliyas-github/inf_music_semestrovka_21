package ru.itis.services;

import com.querydsl.core.util.*;
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

    public List<Instrument> getByName(String[] instrumentNames) {
    	if (ArrayUtils.isEmpty(instrumentNames))
    		return List.of();

        Collection<String> instrumentNamesSet = List.of(instrumentNames);
        return instrumentRepository.findByNameIn(instrumentNamesSet);
    }

    public List<Instrument> getAll() {
        return instrumentRepository.findAll();
    }
}
