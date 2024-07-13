package com.espectro93.examples.springgraphqlbookstore.core.application.order;

import com.espectro93.examples.springgraphqlbookstore.core.domain.order.CustomerId;
import com.espectro93.examples.springgraphqlbookstore.core.domain.order.OrderId;
import com.espectro93.examples.springgraphqlbookstore.core.domain.order.OrderItem;
import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.Command;
import java.util.List;

public record CancelOrderCommand(
    CustomerId customerId,
    OrderId orderId,
    List<OrderItem> orderItems
)
    implements Command {}
