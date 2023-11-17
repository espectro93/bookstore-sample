package com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence;

import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.DomainEvent;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.util.UUID;
@Builder(toBuilder = true)
public record OutboxEntity(@Id String id, DomainEvent event, boolean published, @Version int version) {
    public OutboxEntity(DomainEvent event) {
        this(UUID.randomUUID().toString(), event, false, 1);
    }
}
