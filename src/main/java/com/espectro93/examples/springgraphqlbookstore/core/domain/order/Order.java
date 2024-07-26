package com.espectro93.examples.springgraphqlbookstore.core.domain.order;

import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.AggregateRoot;
import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.DomainEvent;
import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.Identifiable;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    private final Instant date = Instant.now();

    private final List<OrderItem> orderItems;

    private final OrderState orderState;

    private final List<DomainEvent> uncommittedEvents = new ArrayList<>();

    public static Order placeOrder(
        CustomerId customerId,
        List<OrderItem> orderItems
    ) {
        var order = Order.builder()
            .customerId(customerId)
            .orderItems(orderItems)
            .orderState(OrderState.PLACED)
            .build();
        var orderPlacedEvent = order.createOrderPlacedEvent();
        order.uncommittedEvents.add(orderPlacedEvent);
        return order.applyEvent(orderPlacedEvent);
    }

    private OrderPlacedEvent createOrderPlacedEvent() {
        return new OrderPlacedEvent(
            id,
            date,
            customerId,
            orderItems,
            orderState
        );
    }

    public Order cancelOrder() {
        var orderCancelledEvent = new OrderCancelledEvent(
            id,
            customerId,
            orderItems
        );
        uncommittedEvents.add(orderCancelledEvent);
        return applyEvent(orderCancelledEvent);
    }

    @Override
    public Order applyEvent(DomainEvent event) {
        return switch (event) {
            case OrderPlacedEvent ignored -> this;
            case OrderCancelledEvent orderCancelledEvent -> this.toBuilder()
                .orderState(orderCancelledEvent.orderState())
                .build();
            default -> throw new IllegalStateException(
                "Unexpected value: " + event
            );
        };
    }

    public static Order rehydrate(List<DomainEvent> events) {
        return events
            .stream()
            .<Optional<Order>>reduce(
                Optional.empty(),
                (currentOrder, event) ->
                    currentOrder
                        .map(o -> o.applyEvent(event))
                        .or(
                            () ->
                                event instanceof
                                    OrderPlacedEvent orderPlacedEvent
                                    ? Optional.of(
                                        Order.builder()
                                            .id(orderPlacedEvent.aggregateId())
                                            .orderItems(
                                                orderPlacedEvent.orderItems()
                                            )
                                            .customerId(
                                                orderPlacedEvent.customerId()
                                            )
                                            .date(orderPlacedEvent.eventTime())
                                            .build()
                                    )
                                    : Optional.empty()
                        ),
                (existingOrder, newOrder) -> newOrder
            )
            .orElseThrow(
                () ->
                    new IllegalArgumentException(
                        "cannot build order aggregate from events"
                    )
            );
    }
}
