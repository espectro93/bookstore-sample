package com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence.outbox;

import com.espectro93.examples.springgraphqlbookstore.infrastructure.messaging.JmsTopicPublisher;
import java.util.List;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.JmsException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class OutboxScheduler {

    private final OutboxRepository outboxRepository;
    private final JmsTopicPublisher jmsTopicPublisher;

    @Scheduled(fixedRate = 5000)
    @Transactional
    public void handleOutboxQueryEvents() {
        outboxRepository
            .findAll()
            .stream()
            .map(this::processOutboxEntity)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .forEach(outboxRepository::delete);
    }

    private Optional<OutboxEntity> processOutboxEntity(
        OutboxEntity outboxEntity
    ) {
        try {
            jmsTopicPublisher.sendMessage(
                outboxEntity.topicName(),
                outboxEntity
            );
            return Optional.of(outboxEntity);
        } catch (JmsException e) {
            log.error("Messaging error: {}", e.getMessage());
            return Optional.empty();
        }
    }
}
