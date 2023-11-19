package com.espectro93.examples.springgraphqlbookstore.core.application.order;

import com.espectro93.examples.springgraphqlbookstore.core.domain.order.OrderId;
import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.Query;

public record ViewOrderQuery(OrderId orderId) implements Query {
}
