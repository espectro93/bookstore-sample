package com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence;

import com.espectro93.examples.springgraphqlbookstore.core.domain.Order;
import java.time.ZonedDateTime;
import java.util.List;

public record OrderEntity(
    String id,
    String customerId,
    ZonedDateTime date,
    List<String> bookIds
) {
    public static OrderEntity fromDomain(Order order) {
        return null;
    }

    public static Order toDomain(OrderEntity entity) {
        return null;
    }
}
