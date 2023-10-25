package com.espectro93.examples.springgraphqlbookstore.core.ports.out;

import com.espectro93.examples.springgraphqlbookstore.core.domain.book.Book;
import com.espectro93.examples.springgraphqlbookstore.core.domain.book.BookId;

import java.util.List;

public interface BookCommandPort {
    Book loadBy(BookId bookId);
    void save(Book book);
}
