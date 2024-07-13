package com.espectro93.examples.springgraphqlbookstore.core.application.order;

import com.espectro93.examples.springgraphqlbookstore.core.domain.order.Order;
import com.espectro93.examples.springgraphqlbookstore.core.ports.in.CancelOrder;
import com.espectro93.examples.springgraphqlbookstore.core.ports.out.OrderCommandPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Transactional
public class CancelOrderService implements CancelOrder {

    private final OrderCommandPort orderCommandPort;

    @Override
    public Order run(CancelOrderCommand input) {
        var order = orderCommandPort.loadBy(input.orderId());
        order.cancelOrder();
        orderCommandPort.save(order);
        return order;
    }
}
