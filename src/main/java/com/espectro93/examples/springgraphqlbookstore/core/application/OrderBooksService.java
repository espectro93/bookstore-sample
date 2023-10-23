package com.espectro93.examples.springgraphqlbookstore.core.application;

import com.espectro93.examples.springgraphqlbookstore.core.domain.book.Book;
import com.espectro93.examples.springgraphqlbookstore.core.domain.order.Order;
import com.espectro93.examples.springgraphqlbookstore.core.domain.order.OrderItem;
import com.espectro93.examples.springgraphqlbookstore.core.ports.in.OrderBooks;
import com.espectro93.examples.springgraphqlbookstore.core.ports.out.LoadBooks;
import com.espectro93.examples.springgraphqlbookstore.core.ports.out.SaveBooks;
import com.espectro93.examples.springgraphqlbookstore.core.ports.out.SaveOrder;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Transactional
public class OrderBooksService implements OrderBooks {

    private final SaveOrder saveOrder;
    private final LoadBooks loadBooks;
    private final SaveBooks saveBooks;

    @Override
    public Order run(OrderBooksCommand input) {
        var order = Order.createOrder(input.customerId(), input.orderItems());
        var booksToOrder = loadBooks.loadByIds(input.orderItems().stream().map(OrderItem::bookId).toList())
                .stream().collect(Collectors.toMap(Book::getId, Function.identity()));

        var orderedBooks = new ArrayList<Book>();
        for(var orderItem : input.orderItems()) {
            var book = booksToOrder.get(orderItem.bookId());
            orderedBooks.add(book.decreaseStock(orderItem.quantity()));
        }

        saveBooks.save(orderedBooks);
        return saveOrder.save(
            order
        );
    }
}
