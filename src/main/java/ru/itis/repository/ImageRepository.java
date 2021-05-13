package ru.itis.repository;

import org.apache.commons.io.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import javax.imageio.*;
import java.awt.image.*;
import java.io.*;

@Component
public class ImageRepository {
	@Autowired
    private FileRepository fileRepository;

    private final String baseDirectory = "images" + File.separator;

    public byte[] getRaw(String fileName) {
        fileName = baseDirectory + fileName;
        return fileRepository.get(fileName);
    }

    public BufferedImage get(String fileName) {
        fileName = baseDirectory + fileName;
        var file = fileRepository.get(fileName);

        var stream = new ByteArrayInputStream(file);
        try {
            return ImageIO.read(stream);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public String getNewFileName(String format) {
        return fileRepository.getNewFileName(baseDirectory, format);
    }

	public String saveNew(BufferedImage image, String format) {
		var fileName = getNewFileName(format);
		save(fileName, image);
		return fileName;
	}

    public void save(String fileName, BufferedImage image) {
        fileName = baseDirectory + fileName;

        var stream = new ByteArrayOutputStream();
        var format = FilenameUtils.getExtension(fileName);
        try {
            ImageIO.write(image, format, stream);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        fileRepository.save(fileName, stream.toByteArray());
    }
}
