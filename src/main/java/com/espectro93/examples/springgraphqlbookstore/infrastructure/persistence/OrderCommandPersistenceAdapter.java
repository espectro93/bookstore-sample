package com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence;

import com.espectro93.examples.springgraphqlbookstore.core.domain.order.Order;
import com.espectro93.examples.springgraphqlbookstore.core.ports.out.OrderCommandPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderCommandPersistenceAdapter implements OrderCommandPort {

    private final DomainEventRepository domainEventRepository;
    private final OutboxRepository outboxRepository;

    @Override
    public void save(Order order) {
        domainEventRepository.saveAll(order.getUncommittedEvents().stream().map(DomainEventEntity::createFrom).toList());
        outboxRepository.saveAll(order.getUncommittedEvents().stream().map(OutboxEntity::new).toList());
    }
}
