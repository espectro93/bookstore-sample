package com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence;

import com.espectro93.examples.springgraphqlbookstore.core.domain.book.Book;
import com.espectro93.examples.springgraphqlbookstore.core.domain.book.BookId;
import com.espectro93.examples.springgraphqlbookstore.core.domain.book.BookView;
import com.espectro93.examples.springgraphqlbookstore.core.ports.out.BookQueryPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BookQueryPersistenceAdapter implements BookQueryPort {

    private final BookQueryRepository bookQueryRepository;
    @Override
    public BookView loadBy(BookId bookId) {
        return bookQueryRepository.findById(bookId.id()).map(BookQueryEntity::toDomain)
                .orElseThrow(() -> new IllegalArgumentException(String.format("book with bookId: %s not found", bookId.id())));
    }

    @Override
    public Page<BookView> loadByPageable(Pageable pageable) {
        return bookQueryRepository.findAll(pageable).map(BookQueryEntity::toDomain);
    }

    @Override
    public List<BookView> loadByIds(List<BookId> bookIds) {
        return bookQueryRepository.findAllById(bookIds.stream().map(BookId::id).toList())
                .stream().map(BookQueryEntity::toDomain).toList();
    }
}
