package com.espectro93.examples.springgraphqlbookstore.core.application.book;

import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.Command;
import java.util.List;

public record AddBookToCatalogCommand(
    String title,
    List<String> authors,
    String publishDate,
    int pages,
    String isbn,
    String publisherName,
    int stock
)
    implements Command {}
