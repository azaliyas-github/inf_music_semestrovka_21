package ru.itis;

import org.junit.jupiter.api.*;

import java.lang.reflect.*;

public class ReplaceCamelCase extends DisplayNameGenerator.Standard {
	@Override
	public String generateDisplayNameForClass(Class<?> testClass) {
		return super.generateDisplayNameForClass(testClass);
	}

	@Override
	public String generateDisplayNameForNestedClass(Class<?> nestedClass) {
		return replaceCamelCase(super.generateDisplayNameForNestedClass(nestedClass));
	}

	@Override
	public String generateDisplayNameForMethod(Class<?> testClass, Method testMethod) {
		var parameters = DisplayNameGenerator.parameterTypesAsString(testMethod);
		if (parameters.equals("()"))
			parameters = "";
		return this.replaceCamelCase(testMethod.getName()) + parameters;
	}

	private String replaceCamelCase(String camelCase) {
		StringBuilder result = new StringBuilder();

		for (var character : camelCase.toCharArray())
			if (Character.isUpperCase(character)) {
				result.append(' ');
				result.append(Character.toLowerCase(character));
			}
			else
				result.append(character);

		return result.toString();
	}
}
