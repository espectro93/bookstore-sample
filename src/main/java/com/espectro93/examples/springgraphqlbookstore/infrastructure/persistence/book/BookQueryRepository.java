package com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence.book;

import org.springframework.data.couchbase.repository.CouchbaseRepository;

public interface BookQueryRepository extends CouchbaseRepository<BookQueryEntity, String> {
}
