package com.espectro93.examples.springgraphqlbookstore.core.domain.book;

import java.util.List;

public record BookView(
    BookId bookId,
    String title,
    String isbn,
    List<String> authors,
    String publishDate,
    String publisherName,
    int pages,
    int stock
) {}
