package com.espectro93.examples.springgraphqlbookstore.core.application;

import com.espectro93.examples.springgraphqlbookstore.core.domain.BookId;
import com.espectro93.examples.springgraphqlbookstore.core.domain.Order;
import java.time.ZonedDateTime;
import java.util.List;

public record OrderedBooksView(
    String id,
    String customerId,
    ZonedDateTime date,
    List<String> bookIds
) {
    public static OrderedBooksView createFrom(Order order) {
        return new OrderedBooksView(
            order.getId().id(),
            order.getCustomerId().id(),
            order.getDate(),
            order.getBookIds().stream().map(BookId::id).toList()
        );
    }
}
