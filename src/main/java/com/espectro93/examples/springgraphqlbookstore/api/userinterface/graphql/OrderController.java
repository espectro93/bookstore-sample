package com.espectro93.examples.springgraphqlbookstore.api.userinterface.graphql;

import com.espectro93.examples.springgraphqlbookstore.core.application.order.PlaceOrderCommand;
import com.espectro93.examples.springgraphqlbookstore.core.domain.order.CustomerId;
import com.espectro93.examples.springgraphqlbookstore.core.ports.in.PlaceOrder;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderController {

    private final PlaceOrder placeOrder;
    @MutationMapping
    public OrderDto placeOrder(@Argument String customerId, @Argument List<PlaceOrderItemDto> placeOrderItems) {
        return OrderDto.createFrom(
                placeOrder.run(
                        new PlaceOrderCommand(new CustomerId(customerId), placeOrderItems.stream().map(PlaceOrderItemDto::toDomain).toList())
                )
        );
    }

    @MutationMapping
    public OrderDto cancelOrder(@Argument String customerId, @Argument List<PlaceOrderItemDto> placeOrderItems) {
        return OrderDto.createFrom(
                placeOrder.run(
                        new PlaceOrderCommand(new CustomerId(customerId), placeOrderItems.stream().map(PlaceOrderItemDto::toDomain).toList())
                )
        );
    }
}
