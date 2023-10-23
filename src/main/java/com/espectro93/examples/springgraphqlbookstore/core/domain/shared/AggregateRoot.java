package com.espectro93.examples.springgraphqlbookstore.core.domain.shared;

public interface AggregateRoot<T extends Identifiable, E> {
    T getId();

    E applyEvent(DomainEvent event);
}
