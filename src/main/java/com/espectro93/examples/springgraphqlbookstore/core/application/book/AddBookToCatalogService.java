package com.espectro93.examples.springgraphqlbookstore.core.application.book;

import com.espectro93.examples.springgraphqlbookstore.core.domain.book.Book;
import com.espectro93.examples.springgraphqlbookstore.core.ports.in.AddBookToCatalog;
import com.espectro93.examples.springgraphqlbookstore.core.ports.out.BookCommandPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class AddBookToCatalogService implements AddBookToCatalog {

    private final BookCommandPort bookCommandPort;

    @Override
    public Book run(AddBookToCatalogCommand input) {
        var book = Book.addBookToCatalog(input.title(), input.authors(), input.publishDate(), input.pages(), input.isbn(), input.publisherName(), input.stock());
        bookCommandPort.save(book);
        return book;
    }
}
