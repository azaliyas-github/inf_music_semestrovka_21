package ru.itis.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.core.context.*;
import org.springframework.web.bind.annotation.*;
import ru.itis.repository.*;

@RestController
public class ProfileApiController {
	@Autowired
	private ImageRepository imageRepository;

	@RequestMapping(value = "/api/profile/{iser-id}/change/photo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> changePhotoUser (@RequestBody UserChangePhotoRequest imageBase64) {
		// получение имени пользователя из нынешней сессии
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		Boolean bool = some_class.photoChanger(username, imageBase64);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<>(bool, headers, HttpStatus.OK);
	}
}
