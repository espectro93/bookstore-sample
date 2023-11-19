package com.espectro93.examples.springgraphqlbookstore.infrastructure.outbox;

import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.DomainEvent;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class OutboxEventHandlingAspect {

    private final OutboxRepository outboxRepository;

    @After("@annotation(org.springframework.context.event.EventListener)")
    public void afterEventMethod(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();

        if (args.length > 0 && args[0] instanceof DomainEvent event) {
            var state = OutboxState.PROCESSED;
            if (args.length > 1 && args[1] instanceof Exception exception) {
                log.warn("Error processing outbox entity {}", exception.getMessage());
                state = OutboxState.FAILURE;
            }
            var outboxEntity = outboxRepository.findById(event.eventId());
            OutboxState finalState = state;
            outboxEntity.map(entity -> entity.toBuilder().state(finalState).version(entity.version() + 1).build())
                    .ifPresent(outboxRepository::save);
        }
    }
}
