package com.espectro93.examples.springgraphqlbookstore.core.ports.out;

import com.espectro93.examples.springgraphqlbookstore.core.domain.book.Book;

import java.util.List;

public interface SaveBooks {
    void save(List<Book> books);
}
