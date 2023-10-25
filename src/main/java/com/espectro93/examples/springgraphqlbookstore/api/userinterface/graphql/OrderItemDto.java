package com.espectro93.examples.springgraphqlbookstore.api.userinterface.graphql;

import com.espectro93.examples.springgraphqlbookstore.core.domain.order.OrderItem;

public record OrderItemDto(String bookId, String title, String isbn, int quantity) {
    public static OrderItemDto createFrom(OrderItem orderItem) {
        return new OrderItemDto(orderItem.bookId().id(), orderItem.title(), orderItem.isbn(), orderItem.quantity());
    }
}