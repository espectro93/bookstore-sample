package com.espectro93.examples.springgraphqlbookstore.core.application;

import com.espectro93.examples.springgraphqlbookstore.core.domain.Book;
import java.util.List;

public record BookView(
    String id,
    String title,
    List<String> authors,
    String publishDate,
    int pages,
    String isbn,
    String publisherName,
    boolean inStock
) {
    public static BookView createFrom(Book book) {
        return new BookView(
            book.getId().id(),
            book.getTitle(),
            book.getAuthors(),
            book.getPublishDate(),
            book.getPages(),
            book.getIsbn(),
            book.getPublisherName(),
            book.getStock() > 0
        );
    }
}
