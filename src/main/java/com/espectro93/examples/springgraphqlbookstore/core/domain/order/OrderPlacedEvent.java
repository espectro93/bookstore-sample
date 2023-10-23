package com.espectro93.examples.springgraphqlbookstore.core.domain.order;

import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.DomainEvent;
import lombok.Builder;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record OrderPlacedEvent(String eventId, Instant eventTime, OrderId orderId, CustomerId customerId, List<OrderItem> orderItems) implements DomainEvent {
    @Builder
    public OrderPlacedEvent(OrderId orderId, CustomerId customerId, List<OrderItem> orderItems) {
        this(UUID.randomUUID().toString(), Instant.now(), orderId, customerId, orderItems);
    }
}
