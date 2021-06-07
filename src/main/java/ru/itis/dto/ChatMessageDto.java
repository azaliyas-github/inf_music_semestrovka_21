package ru.itis.dto;

import lombok.*;

import java.time.*;

@Data
public class ChatMessageDto {
	private Long senderId;
	private String senderName;

	private Long recipientId;
	private String recipientName;

	private String content;
	private Instant creationTimestamp;
}
