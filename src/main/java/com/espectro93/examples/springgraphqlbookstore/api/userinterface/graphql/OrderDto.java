package com.espectro93.examples.springgraphqlbookstore.api.userinterface.graphql;

import com.espectro93.examples.springgraphqlbookstore.core.domain.order.Order;
import com.espectro93.examples.springgraphqlbookstore.core.domain.order.OrderView;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

public record OrderDto(
    String id,
    String customerId,
    ZonedDateTime date,
    List<OrderItemDto> orderItems,
    String orderState
) {
    public static OrderDto createFrom(Order order) {
        return new OrderDto(
            order.getId().id(),
            order.getCustomerId().id(),
            order.getDate().atZone(ZoneOffset.UTC),
            order
                .getOrderItems()
                .stream()
                .map(OrderItemDto::createFrom)
                .toList(),
            order.getOrderState().name()
        );
    }

    public static OrderDto createFrom(OrderView orderView) {
        return new OrderDto(
            orderView.id().id(),
            orderView.customerId().id(),
            orderView.date().atZone(ZoneOffset.UTC),
            orderView
                .orderItems()
                .stream()
                .map(OrderItemDto::createFrom)
                .toList(),
            orderView.orderState().name()
        );
    }
}
