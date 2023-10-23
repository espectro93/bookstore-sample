package com.espectro93.examples.springgraphqlbookstore.core.domain.shared;

import java.time.Instant;

public interface DomainEvent {
    String eventId();
    Instant eventTime();
}
