package com.espectro93.examples.springgraphqlbookstore.core.application.book;

import com.espectro93.examples.springgraphqlbookstore.core.domain.book.BookView;
import com.espectro93.examples.springgraphqlbookstore.core.ports.in.ViewBook;
import com.espectro93.examples.springgraphqlbookstore.core.ports.out.BookQueryPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class ViewBookService implements ViewBook {

    private final BookQueryPort bookQueryPort;

    @Override
    public BookView run(ViewBookQuery input) {
        return bookQueryPort.loadBy(input.bookId());
    }
}
