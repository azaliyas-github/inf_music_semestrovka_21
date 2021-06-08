package ru.itis.dto;

import lombok.*;
import ru.itis.model.*;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

@Data
@AllArgsConstructor
public class ChatUserDto {
	private Long id;
	private String fullName;
	private String photoFileName;

	public static ChatUserDto from(User user, Profile profile) {
		return new ChatUserDto(
			user.getId(),
			user.getFullName(),
			profile != null ? profile.getPhotoFileName() : "");
	}

	public static List<ChatUserDto> from(List<User> users, List<Profile> profiles) {
		var profilesMap = profiles.stream()
			.collect(Collectors.toMap(Profile::getUserId, Function.identity()));
		return users.stream()
			.map(user -> from(user, profilesMap.getOrDefault(user.getId(), null)))
			.collect(Collectors.toList());
	}
}
