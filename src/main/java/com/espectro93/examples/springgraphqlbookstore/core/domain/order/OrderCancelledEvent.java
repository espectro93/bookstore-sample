package com.espectro93.examples.springgraphqlbookstore.core.domain.order;

import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.DomainEvent;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record OrderCancelledEvent(
    String eventId,
    OrderId aggregateId,
    Instant eventTime,
    String eventType,
    CustomerId customerId,
    List<OrderItem> orderItems,
    OrderState orderState
)
    implements DomainEvent {
    public OrderCancelledEvent(
        OrderId aggregateId,
        CustomerId customerId,
        List<OrderItem> orderItems
    ) {
        this(
            UUID.randomUUID().toString(),
            aggregateId,
            Instant.now(),
            "OrderCancelledEvent",
            customerId,
            orderItems,
            OrderState.CANCELLED
        );
    }
}
