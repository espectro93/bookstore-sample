package com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence;

import org.springframework.data.couchbase.repository.CouchbaseRepository;

public interface OrderRepository
    extends CouchbaseRepository<OrderEntity, String> {}
