package com.espectro93.examples.springgraphqlbookstore.core.application.order;

import com.espectro93.examples.springgraphqlbookstore.core.domain.order.OrderView;
import com.espectro93.examples.springgraphqlbookstore.core.ports.in.ViewOrder;
import com.espectro93.examples.springgraphqlbookstore.core.ports.out.OrderQueryPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class ViewOrderService implements ViewOrder {

    private final OrderQueryPort orderQueryPort;

    @Override
    public OrderView run(ViewOrderQuery input) {
        return orderQueryPort.loadBy(input.orderId());
    }
}
