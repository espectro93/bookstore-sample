package com.espectro93.examples.springgraphqlbookstore.core.ports.out;

import com.espectro93.examples.springgraphqlbookstore.core.domain.Book;
import com.espectro93.examples.springgraphqlbookstore.core.domain.BookId;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LoadBooks {
    Page<Book> loadByPageable(Pageable pageable);
    List<Book> loadByIds(List<BookId> bookIds);
}
