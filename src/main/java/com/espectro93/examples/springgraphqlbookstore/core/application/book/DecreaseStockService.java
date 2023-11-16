package com.espectro93.examples.springgraphqlbookstore.core.application.book;

import com.espectro93.examples.springgraphqlbookstore.core.domain.book.Book;
import com.espectro93.examples.springgraphqlbookstore.core.ports.in.DecreaseStock;
import com.espectro93.examples.springgraphqlbookstore.core.ports.out.BookCommandPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class DecreaseStockService implements DecreaseStock {

    private final BookCommandPort bookCommandPort;

    @Override
    public Book run(DecreaseStockCommand input) {
        var book = bookCommandPort.loadBy(input.bookId());
        book.decreaseStock(input.amount());
        bookCommandPort.save(book);
        return book;
    }
}
