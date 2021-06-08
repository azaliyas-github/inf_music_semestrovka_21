package ru.itis.dto;

import lombok.*;

import java.time.*;

@Data
public class ChatMessageDto {
	private String senderId;
	private String senderName;

	private String recipientId;
	private String recipientName;

	private String content;
	private Instant creationTimestamp;
}
