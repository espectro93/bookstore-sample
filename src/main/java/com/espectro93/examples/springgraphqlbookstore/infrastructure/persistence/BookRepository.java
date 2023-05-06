package com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence;

import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository
    extends CouchbaseRepository<BookEntity, String> {}
