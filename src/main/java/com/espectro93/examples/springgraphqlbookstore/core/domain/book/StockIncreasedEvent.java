package com.espectro93.examples.springgraphqlbookstore.core.domain.book;

import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.DomainEvent;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.Instant;
import java.util.UUID;

public record StockIncreasedEvent(
    String eventId,
    BookId aggregateId,
    String eventType,
    Instant eventTime,
    int quantity
)
    implements DomainEvent {
    public StockIncreasedEvent(BookId bookId, int quantity) {
        this(
            UUID.randomUUID().toString(),
            bookId,
            "StockIncreasedEvent",
            Instant.now(),
            quantity
        );
    }
}
