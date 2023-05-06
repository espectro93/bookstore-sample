package com.espectro93.examples.springgraphqlbookstore.core.ports.out;

import com.espectro93.examples.springgraphqlbookstore.core.domain.Order;

public interface SaveBookOrder {
    Order save(Order order);
}
