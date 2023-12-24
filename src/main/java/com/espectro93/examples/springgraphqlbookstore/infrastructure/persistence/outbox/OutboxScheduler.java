package com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence.outbox;

import com.espectro93.examples.springgraphqlbookstore.infrastructure.messaging.JmsPublisher;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.JmsException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class OutboxScheduler {

    private final OutboxRepository outboxRepository;
    private final JmsPublisher jmsPublisher;

    //TODO: error resistance, each entity should have its own transaction async and either fail or success
    // idea: just spawn an event here, let the process handle it and check in adapter if it is in outbox published=false, if so set true
    // what about optimistic locking here?
    @Scheduled(fixedRate = 1000)
    void handleOutboxQueryEvents() {
        List<OutboxEntity> processedEntities = outboxRepository
                .findAllByState(OutboxState.UNPROCESSED)
                .stream()
                .map(this::processOutboxEntity)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();

        outboxRepository.saveAll(processedEntities);
    }

    private Optional<OutboxEntity> processOutboxEntity(OutboxEntity outboxEntity) {
        try {
            jmsPublisher.sendMessage(outboxEntity.topicName(), outboxEntity);
            return Optional.of(outboxEntity.toBuilder().state(OutboxState.PROCESSED).build());
        } catch (JmsException e) {
            log.error("Messaging error: {}", e.getMessage());
            return Optional.empty();
        }
    }
}
