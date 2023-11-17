package com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence;

import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.DomainEvent;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class OutboxEventHandlingAspect {

    //TODO: set event to PROCESSED or FAILURE
    @After("@annotation(org.springframework.context.event.EventListener)")
    public void afterEventMethod(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        // Assuming the event is the first parameter
        if (args.length > 0 && args[0] instanceof DomainEvent) {
            DomainEvent event = (DomainEvent) args[0];
            // Code to execute after the method (both in case of success and after throwing an exception)
            // Perform database update logic based on the event
            // ...

            // Check if an exception is present as a parameter
            if (args.length > 1 && args[1] instanceof Exception) {
                Exception ex = (Exception) args[1];
                // Code to handle the exception
                // Perform additional database update logic for failure scenarios
                // ...

                // Optionally, rethrow the exception for global handling
                throw new RuntimeException("Event processing failed", ex);
            }
        }
    }
}
