package com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence.order;

import com.espectro93.examples.springgraphqlbookstore.core.domain.order.*;
import java.time.Instant;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.repository.Collection;

@Document
@Collection("order_view")
public record OrderQueryEntity(
    @Id String id,
    @Field String customerId,
    @Field Instant date,
    @Field List<OrderItem> orderItems,
    @Field OrderState orderState
) {
    public OrderView toView() {
        return new OrderView(
            new OrderId(id),
            new CustomerId(id),
            date,
            orderItems,
            orderState
        );
    }
}
