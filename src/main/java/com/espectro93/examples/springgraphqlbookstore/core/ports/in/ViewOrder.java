package com.espectro93.examples.springgraphqlbookstore.core.ports.in;

import com.espectro93.examples.springgraphqlbookstore.core.application.order.ViewOrderQuery;
import com.espectro93.examples.springgraphqlbookstore.core.domain.order.OrderView;
import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.UseCase;

public interface ViewOrder extends UseCase<ViewOrderQuery, OrderView> {}
