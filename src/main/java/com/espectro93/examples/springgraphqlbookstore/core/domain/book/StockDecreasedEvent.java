package com.espectro93.examples.springgraphqlbookstore.core.domain.book;

import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.DomainEvent;
import java.time.Instant;
import java.util.UUID;

public record StockDecreasedEvent(
    String eventId,
    BookId aggregateId,
    String eventType,
    Instant eventTime,
    int quantity
)
    implements DomainEvent {
    public StockDecreasedEvent(BookId bookId, int quantity) {
        this(
            UUID.randomUUID().toString(),
            bookId,
            "StockDecreasedEvent",
            Instant.now(),
            quantity
        );
    }
}
