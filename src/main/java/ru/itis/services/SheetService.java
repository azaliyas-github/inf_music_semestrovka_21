package ru.itis.services;

import com.querydsl.core.*;
import org.apache.commons.collections4.*;
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
	private InstrumentRepository instrumentRepository;

    @Autowired
    private SheetRepository sheetRepository;

    @Autowired
    private PdfRepository pdfRepository;

    @Autowired
    private InstrumentService instrumentService;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PdfUtil pdfUtil;

    public void createSheet(SheetForm form, String userEmail) {
        var preview = pdfUtil.renderPreview(form.getPdf());
        var instruments = instrumentService.getByName(form.getInstruments());
        var imageFileName = imageRepository.saveNew(preview, "jpg");
        var pdfFileName = pdfRepository.saveNew(form.getPdf());
        var user = userRepository.findByEmail(userEmail).get();
        sheetRepository.save(Sheet.builder()
				.title(form.getName())
                .instruments(instruments)
                .composerName(form.getComposer())
                .previewSrc(imageFileName)
                .src(pdfFileName)
	             .createdByUserId(user.getId())
                .build());
    }

    public List<Sheet> getAllSheets() {
        return sheetRepository.findAll();
    }

    public List<Sheet> searchSheets(String query, String[] instrumentNames) {
    	var instruments = instrumentService.getByName(instrumentNames);
        var queryWords = query.split(" ");

		var matchingSheet = QSheet.sheet;
		var sheetQuery = new BooleanBuilder();
		for (var queryWord : queryWords) {
			var containsQueryWord = new BooleanBuilder()
				.or(matchingSheet.composerName.contains(queryWord))
				.or(matchingSheet.title.contains(queryWord));
			sheetQuery.and(containsQueryWord);
		}
		for (var instrument : instruments)
			sheetQuery.and(matchingSheet.instruments.contains(instrument));

		var foundSheets = sheetRepository.findAll(sheetQuery);

        return IterableUtils.toList(foundSheets);
    }

	public List<Sheet> filterSheets(String instrumentName) {
		Instrument instrument = instrumentRepository.findByName(instrumentName);
		if (instrument == null)
			return null;

		return sheetRepository.findByInstruments(instrument);
	}
}
