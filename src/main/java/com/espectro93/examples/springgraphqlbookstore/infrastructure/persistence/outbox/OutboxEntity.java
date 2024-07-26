package com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence.outbox;

import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.DomainEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.Instant;
import lombok.Builder;
import lombok.SneakyThrows;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.repository.Collection;

@Document
@Collection("outbox")
@Builder
public record OutboxEntity(
    @Id String id,
    @Field DomainEvent event,
    @Field String topicName,
    @Field Instant creationTimestamp,
    @Version long version
) {
    public static OutboxEntity createFrom(DomainEvent event) {
        return OutboxEntity.builder()
            .id(event.eventId())
            .event(event)
            .topicName(String.format("%sTopic", event.eventType()))
            .creationTimestamp(Instant.now())
            .build();
    }
}
