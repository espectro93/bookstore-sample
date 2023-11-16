package com.espectro93.examples.springgraphqlbookstore.core.application.book;

import com.espectro93.examples.springgraphqlbookstore.core.domain.book.Book;
import com.espectro93.examples.springgraphqlbookstore.core.ports.in.IncreaseStock;
import com.espectro93.examples.springgraphqlbookstore.core.ports.out.BookCommandPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class IncreaseStockService implements IncreaseStock {

    private final BookCommandPort bookCommandPort;

    @Override
    public Book run(IncreaseStockCommand input) {
        var book = bookCommandPort.loadBy(input.bookId());
        book.increaseStock(input.amount());
        bookCommandPort.save(book);
        return book;
    }
}
