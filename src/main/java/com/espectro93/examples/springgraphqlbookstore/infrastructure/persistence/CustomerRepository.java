package com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence;

import com.espectro93.examples.springgraphqlbookstore.core.domain.order.Customer;
import org.springframework.data.couchbase.repository.CouchbaseRepository;

public interface CustomerRepository
    extends CouchbaseRepository<Customer, String> {}
