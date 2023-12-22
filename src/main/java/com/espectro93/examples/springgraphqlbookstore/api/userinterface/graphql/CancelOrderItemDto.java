package com.espectro93.examples.springgraphqlbookstore.api.userinterface.graphql;

import com.espectro93.examples.springgraphqlbookstore.core.domain.book.BookId;
import com.espectro93.examples.springgraphqlbookstore.core.domain.order.OrderItem;

public record CancelOrderItemDto(
    String bookId,
    String title,
    String isbn,
    int quantity
) {
    public static OrderItem toDomain(PlaceOrderItemDto dto) {
        return new OrderItem(
            new BookId(dto.bookId()),
            dto.title(),
            dto.isbn(),
            dto.quantity()
        );
    }
}
