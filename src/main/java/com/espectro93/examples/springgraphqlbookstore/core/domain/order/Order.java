package com.espectro93.examples.springgraphqlbookstore.core.domain.order;

import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.AggregateRoot;
import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.DomainEvent;
import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.Identifiable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder(toBuilder = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Order implements AggregateRoot<OrderId, Order> {

    @Builder.Default
    private final OrderId id = new OrderId(Identifiable.generateId());

    private final CustomerId customerId;

    @Builder.Default
    private final ZonedDateTime date = ZonedDateTime.now();

    private final List<OrderItem> orderItems;

    private final List<DomainEvent> uncommittedEvents = new ArrayList<>();

    public static Order createOrder(CustomerId customerId, List<OrderItem> orderItems) {
        var order = Order.builder()
                .customerId(customerId)
                .orderItems(orderItems)
                .build();
        var orderPlacedEvent = order.createOrderPlacedEvent();
        return order.applyEvent(orderPlacedEvent);
    }

    private OrderPlacedEvent createOrderPlacedEvent() {
        return new OrderPlacedEvent(id, customerId, orderItems);
    }

    @Override
    public Order applyEvent(DomainEvent event) {
        this.uncommittedEvents.add(event);
        return Order.builder().build();
    }
}
