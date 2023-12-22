package com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence.outbox;

import java.util.List;
import org.springframework.data.couchbase.repository.CouchbaseRepository;

public interface OutboxRepository
    extends CouchbaseRepository<OutboxEntity, String> {
    List<OutboxEntity> findAllByState(OutboxState state);
}
