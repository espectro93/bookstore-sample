package com.espectro93.examples.springgraphqlbookstore.core.application.shared;

import com.espectro93.examples.springgraphqlbookstore.core.application.book.DecreaseStockCommand;
import com.espectro93.examples.springgraphqlbookstore.core.application.book.IncreaseStockCommand;
import com.espectro93.examples.springgraphqlbookstore.core.domain.order.OrderCancelledEvent;
import com.espectro93.examples.springgraphqlbookstore.core.domain.order.OrderPlacedEvent;
import com.espectro93.examples.springgraphqlbookstore.core.ports.in.DecreaseStock;
import com.espectro93.examples.springgraphqlbookstore.core.ports.in.IncreaseStock;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class OrderPlacedHandler {

    private final DecreaseStock decreaseStock;
    private final IncreaseStock increaseStock;

    @Async
    @EventListener
    void handleOrderPlacedEvent(OrderPlacedEvent event) {
        event.orderItems().forEach(item -> decreaseStock.run(new DecreaseStockCommand(item.bookId(), item.quantity())));
    }

    @Async
    @EventListener
    void handleOrderCancelledEvent(OrderCancelledEvent event) {
        event.orderItems().forEach(item -> increaseStock.run(new IncreaseStockCommand(item.bookId(), item.quantity())));
    }
}
