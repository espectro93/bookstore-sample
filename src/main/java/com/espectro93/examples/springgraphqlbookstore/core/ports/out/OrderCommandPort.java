package com.espectro93.examples.springgraphqlbookstore.core.ports.out;

import com.espectro93.examples.springgraphqlbookstore.core.domain.order.Order;

public interface OrderCommandPort {
    void save(Order order);
}
