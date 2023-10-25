package com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence;

import org.springframework.data.couchbase.repository.CouchbaseRepository;

public interface BookQueryRepository extends CouchbaseRepository<BookQueryEntity, String> {
}
