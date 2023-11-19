package com.espectro93.examples.springgraphqlbookstore.infrastructure.outbox;

import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.DomainEvent;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
@Builder(toBuilder = true)
public record OutboxEntity(@Id String id, DomainEvent event, OutboxState state, @Version int version) {
    public OutboxEntity(DomainEvent event) {
        this(event.eventId(), event, OutboxState.UNPROCESSED, 1);
    }
}
