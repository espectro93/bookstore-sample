package com.espectro93.examples.springgraphqlbookstore.core.domain.order;

import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.DomainEvent;

import java.time.Instant;
import java.util.List;

public record OrderCancelledEvent(String eventId, OrderId aggregateId, Instant eventTime, String eventType, CustomerId customerId, List<OrderItem> orderItems, OrderState orderState) implements DomainEvent {
}
