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
import java.util.concurrent.*;
import java.util.stream.*;

@Service
public class ChatService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private Random random;

	private ConcurrentHashMap<Long, Long> userToModeratorMap = new ConcurrentHashMap<>();

	private final String moderatorsGroupId = "moderators";

	public List<ChatUserDto> getUsers() {
		var users = userRepository.findAllByRole(User.Role.USER);
		var profiles = profileRepository.findAllByUserIdIn(
			users.stream().map(User::getId).collect(Collectors.toList()));
		return ChatUserDto.from(users, profiles);
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

		var sender = getUser(message.getSenderId());
		message.setSenderName(sender.getFullName());

		var recipient = getRecipient(sender, message.getRecipientId());
		message.setRecipientId(recipient.getId().toString());
		message.setRecipientName(recipient.getFullName());

		return message;
	}

	private User getUser(String groupId) {
		var userId = Long.parseLong(groupId);
		return userRepository.getOne(userId);
	}

	private User getRecipient(User sender, String recipientGroupId) {
		if (recipientGroupId.equals(moderatorsGroupId)) {
			var moderatorId = userToModeratorMap.getOrDefault(sender.getId(), null);
			if (moderatorId != null)
				return userRepository.getOne(moderatorId);

			var moderators = userRepository.findAllByRole(User.Role.MODERATOR);
			var randomIndex = random.nextInt(moderators.size());

			var randomModerator = moderators.get(randomIndex);
			userToModeratorMap.put(sender.getId(), randomModerator.getId());
			return randomModerator;
		}

		return getUser(recipientGroupId);
	}
}
