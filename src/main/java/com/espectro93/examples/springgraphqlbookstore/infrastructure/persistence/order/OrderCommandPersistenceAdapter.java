package com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence.order;

import com.espectro93.examples.springgraphqlbookstore.core.domain.order.Order;
import com.espectro93.examples.springgraphqlbookstore.core.domain.order.OrderId;
import com.espectro93.examples.springgraphqlbookstore.core.ports.out.OrderCommandPort;
import com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence.DomainEventEntity;
import com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence.DomainEventRepository;
import com.espectro93.examples.springgraphqlbookstore.infrastructure.outbox.OutboxEntity;
import com.espectro93.examples.springgraphqlbookstore.infrastructure.outbox.OutboxRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;

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

    @Override
    public Order loadBy(OrderId orderId) {
        var events = domainEventRepository.findAllById(Collections.singleton(orderId.id()));
        return Order.rehydrate(events.stream().map(DomainEventEntity::data).toList());
    }
}