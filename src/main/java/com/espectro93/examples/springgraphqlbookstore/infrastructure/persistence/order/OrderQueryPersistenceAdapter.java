package com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence.order;

import com.espectro93.examples.springgraphqlbookstore.core.domain.order.OrderId;
import com.espectro93.examples.springgraphqlbookstore.core.domain.order.OrderView;
import com.espectro93.examples.springgraphqlbookstore.core.ports.out.OrderQueryPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class OrderQueryPersistenceAdapter implements OrderQueryPort {

    private final OrderQueryRepository orderQueryRepository;

    @Override
    public OrderView loadBy(OrderId orderId) {
        return orderQueryRepository.findById(orderId.id())
                .map(OrderQueryEntity::toView)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Order with id %s not found.", orderId.id())));
    }
}
