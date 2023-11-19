package com.espectro93.examples.springgraphqlbookstore.core.ports.out;

import com.espectro93.examples.springgraphqlbookstore.core.domain.order.OrderId;
import com.espectro93.examples.springgraphqlbookstore.core.domain.order.OrderView;

public interface OrderQueryPort {
    OrderView loadBy(OrderId orderId);
}
