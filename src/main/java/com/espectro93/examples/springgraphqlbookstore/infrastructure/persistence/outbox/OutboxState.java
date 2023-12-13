package com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence.outbox;

public enum OutboxState {
    PROCESSED,
    UNPROCESSED,
    FAILURE
}
