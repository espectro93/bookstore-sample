package com.espectro93.examples.springgraphqlbookstore.core.domain.book;

import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.DomainEvent;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import lombok.Builder;

public record BookAddedToCatalogEvent(
    String eventId,
    Instant eventTime,
    BookId aggregateId,
    String eventType,
    String title,
    List<String> authors,
    String publishDate,
    int pages,
    String isbn,
    String publisherName,
    int stock
)
    implements DomainEvent {
    @Builder
    public BookAddedToCatalogEvent(
        BookId bookId,
        String title,
        List<String> authors,
        String publishDate,
        int pages,
        String isbn,
        String publisherName,
        int stock
    ) {
        this(
            UUID.randomUUID().toString(),
            Instant.now(),
            bookId,
            "BookAddedToCatalog",
            title,
            authors,
            publishDate,
            pages,
            isbn,
            publisherName,
            stock
        );
    }
}
