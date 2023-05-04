package com.espectro93.examples.springgraphqlbookstore.core.application;

import com.espectro93.examples.springgraphqlbookstore.core.ports.in.OrderBooks;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderBooksService implements OrderBooks {
    @Override
    public OrderedBooksView run(OrderBooksCommand input) {
        return null;
    }
}
