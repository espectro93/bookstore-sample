package com.espectro93.examples.springgraphqlbookstore.core.domain;

import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.BaseEntity;
import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.Identifiable;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Book implements BaseEntity<BookId> {

    @Builder.Default
    private final BookId id = new BookId(Identifiable.generateId());

    private final String title;
    private final List<String> authors;
    private final String publishDate;
    private final int pages;
    private final String isbn;
    private final String publisherName;
    private final int stock;
}
