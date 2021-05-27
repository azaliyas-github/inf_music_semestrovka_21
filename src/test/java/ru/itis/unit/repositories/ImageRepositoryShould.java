package ru.itis.unit.repositories;

import org.junit.jupiter.api.*;
import org.mockito.*;
import ru.itis.repository.*;
import ru.itis.unit.*;

import javax.imageio.*;
import java.io.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ImageRepositoryShould extends UnitTestBase {
	@Mock
	private FileRepository fileRepository;

	@InjectMocks
	private ImageRepository imageRepository;

	private final String baseDirectory = "images" + File.separator;
	private final String fileName = "test.png";

	@Nested
	public class OnGetRaw {
		@Test
		public void throwWhenFileNotExists() {
			when(fileRepository.get(baseDirectory + fileName)).thenThrow(new IllegalStateException());

			assertThrows(IllegalStateException.class, () -> imageRepository.getRaw(fileName));
		}

		@Test
		public void returnFileWhenFileExists() {
			var file = new byte[] { 0 };
			when(fileRepository.get(baseDirectory + fileName)).thenReturn(file);

			assertThat(imageRepository.getRaw(fileName), is(equalTo(file)));
		}
	}

	@Nested
	public class OnGet {
		@Test
		public void throwWhenFileNotExists() {
			when(fileRepository.get(baseDirectory + fileName)).thenThrow(new IllegalStateException());

			assertThrows(IllegalStateException.class, () -> imageRepository.get(fileName));
		}

		@Test
		public void returnImageWhenFileExists() throws IOException {
			var file = get32x16Image().readAllBytes();
			when(fileRepository.get(baseDirectory + fileName)).thenReturn(file);

			var image = imageRepository.get(fileName);

			assertThat(image.getWidth(), is(32));
			assertThat(image.getHeight(), is(16));
		}
	}

	@Nested
	public class OnSave {
		@Test
		public void saveImage() throws IOException {
			var image = ImageIO.read(get32x16Image());
			var expectedFileStream = new ByteArrayOutputStream();
			ImageIO.write(image, "png", expectedFileStream);

			imageRepository.save(fileName, image);

			verify(fileRepository).save(baseDirectory + fileName, expectedFileStream.toByteArray());
		}
	}

	private InputStream get32x16Image() {
		return getClass().getResourceAsStream("32x16-black.png");
	}
}
