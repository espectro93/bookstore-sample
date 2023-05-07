package com.espectro93.examples.springgraphqlbookstore.api.userinterface.graphql;

import com.espectro93.examples.springgraphqlbookstore.core.domain.BookId;
import com.espectro93.examples.springgraphqlbookstore.core.domain.Order;
import java.time.ZonedDateTime;
import java.util.List;

public record OrderDto(
    String id,
    String customerId,
    ZonedDateTime date,
    List<String> bookIds
) {
    public static OrderDto createFrom(Order order) {
        return new OrderDto(
            order.getId().id(),
            order.getCustomerId().id(),
            order.getDate(),
            order.getBookIds().stream().map(BookId::id).toList()
        );
    }
}
