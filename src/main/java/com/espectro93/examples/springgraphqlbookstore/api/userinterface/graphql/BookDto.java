package com.espectro93.examples.springgraphqlbookstore.api.userinterface.graphql;

import com.espectro93.examples.springgraphqlbookstore.core.domain.book.Book;
import java.util.List;

public record BookDto(
    String id,
    String title,
    List<String> authors,
    String publishDate,
    int pages,
    String isbn,
    String publisherName,
    boolean inStock
) {
    public static BookDto createFrom(Book book) {
        return new BookDto(
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
