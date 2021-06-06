package ru.itis.aspects;

import lombok.extern.log4j.*;
import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.util.*;

import java.util.*;

@Component
@Aspect
@Log4j2
public class LoggingAspect {
	@Around("@annotation(LogExecutionInfo)")
	public Object logExecutionInfo(ProceedingJoinPoint joinPoint)
			throws Throwable {
		var methodDescription = "method \"" + joinPoint.getSignature().toString() + "\"";
		log.info("Executing " + methodDescription
			+ " with parameters:" + System.lineSeparator() + Arrays.toString(joinPoint.getArgs()));

		var stopWatch = new StopWatch();
		stopWatch.start();
		var result = joinPoint.proceed();
		stopWatch.stop();

		log.info("Executed " + methodDescription + " in " + stopWatch.getTotalTimeSeconds() + " s");
		return result;
	}
}
