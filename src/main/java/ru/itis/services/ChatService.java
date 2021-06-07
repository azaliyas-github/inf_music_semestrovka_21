package ru.itis.services;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import ru.itis.dto.*;
import ru.itis.exceptions.*;
import ru.itis.repository.*;

import java.time.*;

@Service
public class ChatService {
	@Autowired
	private UserRepository userRepository;

	@Transactional
	public ChatMessageDto saveMessage(ChatMessageDto message) {
		if (message.getSenderId().equals(message.getRecipientId()))
			throw new BusinessException("Can't send message from sender " + message.getSenderId() + " to itself");
		message.setCreationTimestamp(Instant.now());

		var sender = userRepository.getOne(message.getSenderId());
		var recipient = userRepository.getOne(message.getRecipientId());

		message.setSenderName(sender.getFullName());
		message.setRecipientName(recipient.getFullName());

		return message;
	}
}
