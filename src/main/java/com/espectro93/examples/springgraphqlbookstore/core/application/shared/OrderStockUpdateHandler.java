package com.espectro93.examples.springgraphqlbookstore.core.application.shared;

import com.espectro93.examples.springgraphqlbookstore.core.domain.order.OrderCancelledEvent;
import com.espectro93.examples.springgraphqlbookstore.core.domain.order.OrderPlacedEvent;
import com.espectro93.examples.springgraphqlbookstore.core.ports.out.BookCommandPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class OrderStockUpdateHandler {

    private final BookCommandPort bookCommandPort;

    @JmsListener(destination = "OrderPlacedTopic")
    void handleOrderPlacedEvent(OrderPlacedEvent event) {
        event
            .orderItems()
            .forEach(item -> {
                var book = bookCommandPort.loadBy(item.bookId());
                book = book.decreaseStock(item.quantity());
                bookCommandPort.save(book);
            });
    }

    @JmsListener(destination = "OrderCancelledTopic")
    void handleOrderCancelledEvent(OrderCancelledEvent event) {
        event
            .orderItems()
            .forEach(item -> {
                var book = bookCommandPort.loadBy(item.bookId());
                book = book.increaseStock(item.quantity());
                bookCommandPort.save(book);
            });
    }
}
