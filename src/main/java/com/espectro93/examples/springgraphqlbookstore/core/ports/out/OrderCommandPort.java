package com.espectro93.examples.springgraphqlbookstore.core.ports.out;

import com.espectro93.examples.springgraphqlbookstore.core.domain.order.Order;
import com.espectro93.examples.springgraphqlbookstore.core.domain.order.OrderId;

public interface OrderCommandPort {
    void save(Order order);

    Order loadBy(OrderId orderId);
}
