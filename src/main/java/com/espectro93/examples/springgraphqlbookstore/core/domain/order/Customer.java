package com.espectro93.examples.springgraphqlbookstore.core.domain.order;

import com.espectro93.examples.springgraphqlbookstore.core.domain.book.Book;
import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.Identifiable;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Customer {

    @Builder.Default
    private final CustomerId id = new CustomerId(Identifiable.generateId());

    private final String email;
    private final String firstName;
    private final String lastName;

    public Order buy(List<Book> books) {
        return Order
            .builder()
            .customerId(id)
            .orderItems(books.stream().map(Book::getId).toList())
            .build();
    }
}
