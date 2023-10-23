package com.espectro93.examples.springgraphqlbookstore.core.application;

import com.espectro93.examples.springgraphqlbookstore.core.domain.order.CustomerId;
import com.espectro93.examples.springgraphqlbookstore.core.domain.order.OrderItem;
import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.Command;
import java.util.List;

public record OrderBooksCommand(CustomerId customerId, List<OrderItem> orderItems)
    implements Command {}
