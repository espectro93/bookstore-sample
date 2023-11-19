package com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence.book;

import com.espectro93.examples.springgraphqlbookstore.core.domain.book.*;
import com.espectro93.examples.springgraphqlbookstore.core.ports.out.BookQueryPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BookQueryPersistenceAdapter implements BookQueryPort {

    private final BookQueryRepository bookQueryRepository;

    @Override
    public BookView loadBy(BookId bookId) {
        return bookQueryRepository.findById(bookId.id()).map(BookQueryEntity::toView)
                .orElseThrow(() -> new IllegalArgumentException(String.format("book with bookId: %s not found", bookId.id())));
    }

    @Override
    public Page<BookView> loadByPageable(Pageable pageable) {
        return bookQueryRepository.findAll(pageable).map(BookQueryEntity::toView);
    }

    @Override
    public List<BookView> loadByIds(List<BookId> bookIds) {
        return bookQueryRepository.findAllById(bookIds.stream().map(BookId::id).toList())
                .stream().map(BookQueryEntity::toView).toList();
    }

    @Async
    @EventListener
    void handleBookAddedToCatalogEvent(BookAddedToCatalogEvent event) {
        if (bookQueryRepository.existsById(event.aggregateId().id())) {
            throw new IllegalArgumentException("That book already exists");
        }
        var bookView = BookQueryEntity.fromEvent(event);
        bookQueryRepository.save(bookView);
    }

    @Async
    @EventListener
    void handleStockDecreasedEvent(StockDecreasedEvent event) {
        var bookQueryEntity = bookQueryRepository.findById(event.aggregateId().id())
                .orElseThrow(() -> new IllegalArgumentException(String.format("Book with id %s does not exist.", event.aggregateId().id())));
        var bookView = bookQueryEntity.fromEvent(event);
        bookQueryRepository.save(bookView);
    }

    @Async
    @EventListener
    void handleStockIncreasedEvent(StockIncreasedEvent event) {
        var bookQueryEntity = bookQueryRepository.findById(event.aggregateId().id())
                .orElseThrow(() -> new IllegalArgumentException(String.format("Book with id %s does not exist.", event.aggregateId().id())));
        var bookView = bookQueryEntity.fromEvent(event);
        bookQueryRepository.save(bookView);
    }
}
