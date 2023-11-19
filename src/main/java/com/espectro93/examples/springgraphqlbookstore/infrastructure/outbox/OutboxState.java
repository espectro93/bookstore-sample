package com.espectro93.examples.springgraphqlbookstore.infrastructure.outbox;

public enum OutboxState {
    PROCESSED,
    UNPROCESSED,
    FAILURE
}
