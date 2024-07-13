package com.espectro93.examples.springgraphqlbookstore.core.domain.order;

import java.time.Instant;
import java.util.List;

public record OrderView(
    OrderId id,
    CustomerId customerId,
    Instant date,
    List<OrderItem> orderItems,
    OrderState orderState
) {}
