package com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence.order;

import com.espectro93.examples.springgraphqlbookstore.core.domain.order.OrderCancelledEvent;
import com.espectro93.examples.springgraphqlbookstore.core.domain.order.OrderId;
import com.espectro93.examples.springgraphqlbookstore.core.domain.order.OrderPlacedEvent;
import com.espectro93.examples.springgraphqlbookstore.core.domain.order.OrderView;
import com.espectro93.examples.springgraphqlbookstore.core.ports.out.OrderQueryPort;
import com.espectro93.examples.springgraphqlbookstore.infrastructure.error.NotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class OrderQueryPersistenceAdapter implements OrderQueryPort {

    private final OrderQueryRepository orderQueryRepository;

    @Override
    public OrderView loadBy(OrderId orderId) {
        return orderQueryRepository
            .findById(orderId.id())
            .map(OrderQueryEntity::toView)
            .orElseThrow(
                () ->
                    new NotFoundException(
                        String.format(
                            "Order with id %s not found.",
                            orderId.id()
                        )
                    )
            );
    }

    @JmsListener(destination = "OrderPlacedTopic")
    void handleOrderPlacedEvent(OrderPlacedEvent event) {
        var orderQueryEntity = OrderQueryEntity.fromEvent(event);
        orderQueryRepository.save(orderQueryEntity);
    }

    @JmsListener(destination = "OrderCancelledTopic")
    void handleOrderCancelledEvent(OrderCancelledEvent event) {
        var orderQueryEntity = orderQueryRepository
            .findById(event.aggregateId().id())
            .orElseThrow(
                () ->
                    new IllegalArgumentException(
                        String.format(
                            "Order with id %s does not exist.",
                            event.aggregateId().id()
                        )
                    )
            );
        orderQueryEntity = orderQueryEntity.fromEvent(event);
        orderQueryRepository.save(orderQueryEntity);
    }
}
