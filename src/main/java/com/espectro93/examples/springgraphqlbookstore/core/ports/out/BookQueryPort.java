package com.espectro93.examples.springgraphqlbookstore.core.ports.out;

import com.espectro93.examples.springgraphqlbookstore.core.domain.book.Book;
import com.espectro93.examples.springgraphqlbookstore.core.domain.book.BookId;
import com.espectro93.examples.springgraphqlbookstore.core.domain.book.BookView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookQueryPort {
    BookView loadBy(BookId bookId);
    Page<BookView> loadByPageable(Pageable pageable);
    List<BookView> loadByIds(List<BookId> bookIds);
}
