package ru.itis.model;

import lombok.*;

import javax.persistence.*;
import java.io.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MethodCounter {
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@EqualsAndHashCode
	@Embeddable
	public static class Key implements Serializable {
		private String className;
		private String methodName;
	}

	@EmbeddedId
	private Key key;
	private int count;
}
