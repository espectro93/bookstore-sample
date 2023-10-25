package com.espectro93.examples.springgraphqlbookstore.core.domain.order;

import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.DomainEvent;
import lombok.Builder;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record OrderPlacedEvent(String eventId, OrderId aggregateId, Instant eventTime, String eventType, CustomerId customerId, List<OrderItem> orderItems, OrderState orderState) implements DomainEvent {
    @Builder
    public OrderPlacedEvent(OrderId orderId, CustomerId customerId, List<OrderItem> orderItems, OrderState orderState) {
        this(UUID.randomUUID().toString(), orderId, Instant.now(), "OrderPlacedEvent", customerId, orderItems, orderState);
    }
}
