package com.espectro93.examples.springgraphqlbookstore.core.ports.out;

import com.espectro93.examples.springgraphqlbookstore.core.domain.Book;
import com.espectro93.examples.springgraphqlbookstore.core.domain.BookId;

public interface LoadBook {
    Book loadBy(BookId bookId);
}
