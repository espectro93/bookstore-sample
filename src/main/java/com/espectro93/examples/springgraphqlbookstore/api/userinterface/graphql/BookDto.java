package com.espectro93.examples.springgraphqlbookstore.api.userinterface.graphql;

import com.espectro93.examples.springgraphqlbookstore.core.domain.book.BookView;

import java.util.List;

public record BookDto(
    String id,
    String title,
    List<String> authors,
    String publishDate,
    int pages,
    String isbn,
    String publisherName,
    int stock
) {
    public static BookDto createFrom(BookView bookView) {
        return new BookDto(
            bookView.bookId().id(),
                bookView.title(),
                bookView.authors(),
                bookView.publishDate(),
                bookView.pages(),
                bookView.isbn(),
                bookView.publisherName(),
                bookView.stock()
        );
    }
}
