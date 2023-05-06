package com.espectro93.examples.springgraphqlbookstore.core.domain.shared;

public interface BaseEntity<T extends Identifiable> {
    T getId();
}
