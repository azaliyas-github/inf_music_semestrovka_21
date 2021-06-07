package ru.itis.services;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import ru.itis.dto.*;
import ru.itis.exceptions.*;
import ru.itis.model.*;
import ru.itis.repository.*;

import java.time.*;
import java.util.*;
import java.util.stream.*;

@Service
public class ChatService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProfileRepository profileRepository;

	public List<ChatUserDto> getRecipients(Long senderId) {
		var sender = userRepository.getOne(senderId);
		var recipients = sender.isAdmin()
			? userRepository.findAllByRole(User.Role.USER)
			: userRepository.findAllByRole(User.Role.MODERATOR);

		var recipientProfiles = profileRepository.findAllByUserIdIn(
			recipients.stream().map(User::getId).collect(Collectors.toList()));
		return ChatUserDto.from(recipients, recipientProfiles);
	}

	public ChatUserDto getUser(Long userId) {
		var user = userRepository.getOne(userId);
		var profile = profileRepository.findByUserId(userId).orElse(null);
		return ChatUserDto.from(user, profile);
	}

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
