package com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence.outbox;

import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.DomainEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Builder;
import lombok.SneakyThrows;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.repository.Collection;

@Document
@Collection("outbox")
@Builder(toBuilder = true)
public record OutboxEntity(
    @Id String id,
    @Field DomainEvent event,
    @Field OutboxState state,
    @Field String topicName,
    @Version long version
) {
    public static OutboxEntity createFrom(DomainEvent event) {
        return OutboxEntity.builder()
            .id(event.eventId())
            .event(event)
            .state(OutboxState.UNPROCESSED)
            .topicName(String.format("%sTopic", event.eventType()))
            .build();
    }

    @SneakyThrows
    public String serializeEvent() {
        return new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .writeValueAsString(this.event);
    }
}
