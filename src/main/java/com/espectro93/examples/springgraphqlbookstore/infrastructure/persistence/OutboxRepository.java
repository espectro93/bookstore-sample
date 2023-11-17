package com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence;

import org.springframework.data.couchbase.repository.CouchbaseRepository;

import java.util.List;

public interface OutboxRepository extends CouchbaseRepository<OutboxEntity, String> {
    List<OutboxEntity> findAllByPublishedFalse();
}
