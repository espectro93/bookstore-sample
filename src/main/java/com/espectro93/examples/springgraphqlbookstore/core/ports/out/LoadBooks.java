package com.espectro93.examples.springgraphqlbookstore.core.ports.out;

import com.espectro93.examples.springgraphqlbookstore.core.domain.Book;
import com.espectro93.examples.springgraphqlbookstore.core.domain.BookId;
import java.util.List;

public interface LoadBooks {
    List<Book> loadByPageable(int page, int size);
    List<Book> loadByIds(List<BookId> bookIds);
}
