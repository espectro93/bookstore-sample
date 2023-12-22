package com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence.outbox;

import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.DomainEvent;
import java.util.UUID;
import lombok.Builder;
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
    @Version long version
) {
    public static OutboxEntity createFrom(DomainEvent event) {
        return OutboxEntity
            .builder()
            .id(UUID.randomUUID().toString())
            .event(event)
            .state(OutboxState.UNPROCESSED)
            .build();
    }
}
