package com.espectro93.examples.springgraphqlbookstore.core.domain.book;

import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.DomainEvent;

import java.time.Instant;
import java.util.UUID;

public record StockDecreasedEvent(String eventId, Instant eventTime, int quantity) implements DomainEvent {

    public StockDecreasedEvent(int quantity){
        this(UUID.randomUUID().toString(), Instant.now(), quantity);
    }
}
