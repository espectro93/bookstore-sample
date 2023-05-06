package com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence;

import com.espectro93.examples.springgraphqlbookstore.core.domain.Order;
import com.espectro93.examples.springgraphqlbookstore.core.ports.out.SaveBookOrder;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderPersistenceAdapter implements SaveBookOrder {

    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;

    @Override
    public Order save(Order order) {
        //TODO: CREATE ATOMIC OPERATION FOR CREATING THE ORDER AND MODIFYING THE STOCK
        return null;
    }
}
