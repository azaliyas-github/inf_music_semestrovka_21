package ru.itis.repository;

import org.icepdf.core.pobjects.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.io.*;

@Component
public class PdfRepository {
	@Autowired
    private FileRepository fileRepository;

    private final String baseDirectory = "pdf" + File.separator;

    public byte[] getRaw(String fileName) {
        fileName = baseDirectory + fileName;
        return fileRepository.get(fileName);
    }

    public Document get(String fileName) {
        fileName = baseDirectory + fileName;
        var file = fileRepository.get(fileName);

        var pdf = new Document();
        try {
            pdf.setByteArray(file, 0, file.length, fileName);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return pdf;
    }

    public String getNewFileName() {
        return fileRepository.getNewFileName(baseDirectory, "pdf");
    }

    public String saveNew(Document pdf) {
        var fileName = getNewFileName();
        save(fileName, pdf);
        return fileName;
    }

    public void save(String fileName, Document pdf) {
        fileName = baseDirectory + fileName;

        var stream = new ByteArrayOutputStream();
        try {
            pdf.writeToOutputStream(stream);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        fileRepository.save(fileName, stream.toByteArray());
    }
}
