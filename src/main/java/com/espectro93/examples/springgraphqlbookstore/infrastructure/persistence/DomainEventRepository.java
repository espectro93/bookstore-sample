package com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence;

import java.util.List;
import org.springframework.data.couchbase.repository.CouchbaseRepository;

public interface DomainEventRepository
    extends CouchbaseRepository<DomainEventEntity, String> {}
