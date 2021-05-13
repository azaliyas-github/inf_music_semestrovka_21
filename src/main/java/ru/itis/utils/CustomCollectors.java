package ru.itis.utils;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public abstract class CustomCollectors {
	public static <T> BinaryOperator<T> throwingMerger() {
		return (first, second) -> {
			throw new IllegalStateException("Duplicate key for values " + first + " and " + second);
		};
	}

	public static <T, K, U> Collector<T, ?, Map<K, U>> toSortedMap(
		Function<? super T, ? extends K> keyMapper,
		Function<? super T, ? extends U> valueMapper) {
		return Collectors.toMap(keyMapper, valueMapper, throwingMerger(), TreeMap::new);
	}
}
