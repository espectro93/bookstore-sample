package com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence;

import com.espectro93.examples.springgraphqlbookstore.core.domain.order.Customer;

public record CustomerEntity(
    String id,
    String email,
    String firstName,
    String lastName
) {
    public static Customer toDomain(CustomerEntity entity) {
        return null;
    }

    public static CustomerEntity fromDomain(Customer customer) {
        return null;
    }
}
