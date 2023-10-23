package com.espectro93.examples.springgraphqlbookstore.core.domain.book;

import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.DomainEvent;

import java.time.Instant;
import java.util.UUID;

public record StockIncreasedEvent(String eventId, Instant eventTime, int quantity) implements DomainEvent {
    public StockIncreasedEvent(int quantity) {
        this(UUID.randomUUID().toString(), Instant.now(), quantity);
    }
}
