package ru.itis.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.*;
import ru.itis.services.*;

@Controller
@RequestMapping("chat")
public class ChatController {
	@Autowired
	private ChatService chatService;

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@MessageMapping("send")
	public void sendMessage(@Payload ChatMessageDto message) {
		message = chatService.saveMessage(message);

		messagingTemplate.convertAndSendToUser(
			message.getSenderId().toString(),
			"/messages",
			message);
		messagingTemplate.convertAndSendToUser(
			message.getRecipientId().toString(),
			"/messages",
			message);
	}

	@GetMapping("users/{user-id}")
	public @ResponseBody ChatUserDto getUser(@PathVariable("user-id") Long userId) {
		return chatService.getUser(userId);
	}
}
