package com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence.order;

import org.springframework.data.couchbase.repository.CouchbaseRepository;

public interface OrderQueryRepository
    extends CouchbaseRepository<OrderQueryEntity, String> {}
