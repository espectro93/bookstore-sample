package com.espectro93.examples.springgraphqlbookstore.api.userinterface.graphql;

import com.espectro93.examples.springgraphqlbookstore.core.application.order.CancelOrderCommand;
import com.espectro93.examples.springgraphqlbookstore.core.application.order.PlaceOrderCommand;
import com.espectro93.examples.springgraphqlbookstore.core.application.order.ViewOrderQuery;
import com.espectro93.examples.springgraphqlbookstore.core.domain.order.CustomerId;
import com.espectro93.examples.springgraphqlbookstore.core.domain.order.OrderId;
import com.espectro93.examples.springgraphqlbookstore.core.ports.in.CancelOrder;
import com.espectro93.examples.springgraphqlbookstore.core.ports.in.PlaceOrder;
import com.espectro93.examples.springgraphqlbookstore.core.ports.in.ViewOrder;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderController {

    private final ViewOrder viewOrder;
    private final PlaceOrder placeOrder;
    private final CancelOrder cancelOrder;

    @QueryMapping
    public OrderDto viewOrder(@Argument String orderId) {
        return OrderDto.createFrom(
            viewOrder.run(new ViewOrderQuery(new OrderId(orderId)))
        );
    }

    @MutationMapping
    public OrderDto placeOrder(
        @Argument String customerId,
        @Argument List<PlaceOrderItemDto> placeOrderItems
    ) {
        return OrderDto.createFrom(
            placeOrder.run(
                new PlaceOrderCommand(
                    new CustomerId(customerId),
                    placeOrderItems
                        .stream()
                        .map(PlaceOrderItemDto::toDomain)
                        .toList()
                )
            )
        );
    }

    @MutationMapping
    public OrderDto cancelOrder(
        @Argument String customerId,
        @Argument String orderId,
        @Argument List<PlaceOrderItemDto> placeOrderItems
    ) {
        return OrderDto.createFrom(
            cancelOrder.run(
                new CancelOrderCommand(
                    new CustomerId(customerId),
                    new OrderId(orderId),
                    placeOrderItems
                        .stream()
                        .map(PlaceOrderItemDto::toDomain)
                        .toList()
                )
            )
        );
    }
}
