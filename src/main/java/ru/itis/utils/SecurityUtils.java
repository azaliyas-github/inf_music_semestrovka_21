package ru.itis.utils;

import org.springframework.security.core.context.*;
import ru.itis.security.details.*;

public abstract class SecurityUtils {
	public static UserDetailsImpl getUserDetails() {
		return (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
