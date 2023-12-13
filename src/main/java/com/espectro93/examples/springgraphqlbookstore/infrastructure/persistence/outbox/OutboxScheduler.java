package com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence.outbox;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class OutboxScheduler {

    private final OutboxRepository outboxRepository;
    private final ApplicationEventPublisher applicationEventPublisher;


    //TODO: error resistance, each entity should have its own transaction async and either fail or success
    // idea: just spawn an event here, let the process handle it and check in adapter if it is in outbox published=false, if so set true
    // what about optimistic locking here?
    @Scheduled
    void handleOutboxQueryEvents() {
        var outboxEntities = outboxRepository.findAllByState(OutboxState.UNPROCESSED);
        outboxEntities.forEach(entity -> applicationEventPublisher.publishEvent(entity.event()));
    }
}
