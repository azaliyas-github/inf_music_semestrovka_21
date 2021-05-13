package ru.itis.dto;

import lombok.*;
import org.icepdf.core.pobjects.*;
import org.springframework.web.multipart.*;

@Data
@Getter
public class SheetForm {
    private String name;
    private String composer;
    private String[] instruments;
    private MultipartFile file;

    public Document getPdf() {
        var pdf = new Document();
        try {
            var pdfBytes = file.getBytes();
            pdf.setByteArray(pdfBytes, 0, pdfBytes.length, file.getName());
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return pdf;
    }
}
