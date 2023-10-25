package com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence;

import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.DomainEvent;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

@Document
public record DomainEventEntity(@Id String eventId, @Field String aggregateId, @Field String eventType, @Field DomainEvent data) {
    public static DomainEventEntity createFrom(DomainEvent event) {
        return new DomainEventEntity(event.eventId(), event.aggregateId().id(), event.eventType(), event);
    }
}
