package ru.itis.services;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import ru.itis.dto.*;
import ru.itis.model.*;
import ru.itis.repository.*;
import ru.itis.utils.*;

import java.util.*;

@Service
public class SheetService {
    @Autowired
    private SheetRepository sheetRepository;

    @Autowired
    private PdfRepository pdfRepository;

    @Autowired
    private InstrumentService instrumentService;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private PdfUtil pdfUtil;

    public void createSheet(SheetForm form) {
        var preview = pdfUtil.renderPreview(form.getPdf());
        var instruments = instrumentService.getByName(form.getInstruments());
        var imageFileName = imageRepository.saveNew(preview, "jpg");
        var pdfFileName = pdfRepository.saveNew(form.getPdf());
        sheetRepository.save(Sheet.builder()
				.title(form.getName())
                .instruments(instruments)
                .composerName(form.getComposer())
                .previewSrc(imageFileName)
                .src(pdfFileName)
                .build());
    }

    public List<Sheet> getAllSheets() {
        return sheetRepository.findAll();
    }

}
