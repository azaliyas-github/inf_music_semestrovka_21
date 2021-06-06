package ru.itis.aspects;

import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import ru.itis.services.*;

@Component
@Aspect
public class MethodCounterAspect {
	@Autowired private MethodCounterService counterService;

	@Before("within(ru.itis.controllers.*)")
	public void incrementMethodCounter(JoinPoint joinPoint) {
		var methodSignature = joinPoint.getSignature();
		var controllerName = methodSignature.getDeclaringType().getSimpleName();
		var methodName = methodSignature.getName();

		counterService.increment(controllerName, methodName);
	}
}
