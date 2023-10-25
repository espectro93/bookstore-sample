package com.espectro93.examples.springgraphqlbookstore.core.application.order;

import com.espectro93.examples.springgraphqlbookstore.core.domain.order.Order;
import com.espectro93.examples.springgraphqlbookstore.core.ports.in.PlaceOrder;
import com.espectro93.examples.springgraphqlbookstore.core.ports.out.OrderCommandPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Transactional
public class PlaceOrderService implements PlaceOrder {

    private final OrderCommandPort orderCommandPort;
    private final OrderEventPublisher orderEventPublisher;

    @Override
    public Order run(PlaceOrderCommand input) {
        var order = Order.placeOrder(input.customerId(), input.orderItems());

//        var booksToOrder = loadBooks.loadByIds(input.orderItems().stream().map(OrderItem::bookId).toList())
//                .stream().collect(Collectors.toMap(Book::getId, Function.identity()));
//
//        var orderedBooks = new ArrayList<Book>();
//        for(var orderItem : input.orderItems()) {
//            var book = booksToOrder.get(orderItem.bookId());
//            orderedBooks.add(book.decreaseStock(orderItem.quantity()));
//        }

        orderCommandPort.save(order);
        orderEventPublisher.publish(order.getUncommittedEvents()); //TODO: outbox
        return order;
    }
}
