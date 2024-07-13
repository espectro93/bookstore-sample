package com.espectro93.examples.springgraphqlbookstore.core.application.order;

import com.espectro93.examples.springgraphqlbookstore.core.domain.order.Order;
import com.espectro93.examples.springgraphqlbookstore.core.ports.in.PlaceOrder;
import com.espectro93.examples.springgraphqlbookstore.core.ports.out.OrderCommandPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Transactional
public class PlaceOrderService implements PlaceOrder {

    private final OrderCommandPort orderCommandPort;

    @Override
    public Order run(PlaceOrderCommand input) {
        var order = Order.placeOrder(input.customerId(), input.orderItems());
        orderCommandPort.save(order);
        return order;
    }
}
