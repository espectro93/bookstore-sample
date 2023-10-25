package com.espectro93.examples.springgraphqlbookstore.core.ports.in;

import com.espectro93.examples.springgraphqlbookstore.core.application.order.PlaceOrderCommand;
import com.espectro93.examples.springgraphqlbookstore.core.domain.order.Order;
import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.UseCase;

public interface PlaceOrder extends UseCase<PlaceOrderCommand, Order> {}
