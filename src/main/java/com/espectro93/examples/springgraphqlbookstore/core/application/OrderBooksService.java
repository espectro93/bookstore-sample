package com.espectro93.examples.springgraphqlbookstore.core.application;

import com.espectro93.examples.springgraphqlbookstore.core.domain.Order;
import com.espectro93.examples.springgraphqlbookstore.core.ports.in.OrderBooks;
import com.espectro93.examples.springgraphqlbookstore.core.ports.out.LoadBooks;
import com.espectro93.examples.springgraphqlbookstore.core.ports.out.SaveBookOrder;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderBooksService implements OrderBooks {

    private final SaveBookOrder saveBookOrder;

    @Override
    public OrderedBooksView run(OrderBooksCommand input) {
        return OrderedBooksView.createFrom(
            saveBookOrder.save(
                Order
                    .builder()
                    .customerId(input.customerId())
                    .bookIds(input.bookIds())
                    .build()
            )
        );
    }
}
