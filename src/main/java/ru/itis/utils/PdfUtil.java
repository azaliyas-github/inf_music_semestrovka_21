package ru.itis.utils;

import org.icepdf.core.pobjects.*;
import org.icepdf.core.util.*;
import org.springframework.stereotype.*;

import java.awt.image.*;

@Component
public class PdfUtil {
    public BufferedImage renderPreview(Document pdf) {
        try {
            return (BufferedImage)pdf.getPageImage(
                    0, GraphicsRenderingHints.PRINT, Page.BOUNDARY_CROPBOX, 0, 1);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
