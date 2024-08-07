package com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence.book;

import com.espectro93.examples.springgraphqlbookstore.core.domain.book.*;
import com.espectro93.examples.springgraphqlbookstore.core.ports.out.BookQueryPort;
import com.espectro93.examples.springgraphqlbookstore.infrastructure.error.NotFoundException;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BookQueryPersistenceAdapter implements BookQueryPort {

    private final BookQueryRepository bookQueryRepository;

    @Override
    public BookView loadBy(BookId bookId) {
        return bookQueryRepository
            .findById(bookId.id())
            .map(BookQueryEntity::toView)
            .orElseThrow(
                () ->
                    new NotFoundException(
                        String.format(
                            "book with bookId: %s not found",
                            bookId.id()
                        )
                    )
            );
    }

    @Override
    public Page<BookView> loadByPageable(Pageable pageable) {
        return bookQueryRepository
            .findAll(pageable)
            .map(BookQueryEntity::toView);
    }

    @Override
    public List<BookView> loadByIds(List<BookId> bookIds) {
        return bookQueryRepository
            .findAllById(bookIds.stream().map(BookId::id).toList())
            .stream()
            .map(BookQueryEntity::toView)
            .toList();
    }

    @JmsListener(destination = "BookAddedToCatalogTopic")
    void handleBookAddedToCatalogEvent(BookAddedToCatalogEvent event) {
        if (bookQueryRepository.existsById(event.aggregateId().id())) {
            throw new IllegalArgumentException("That book already exists");
        }
        var bookQueryEntity = BookQueryEntity.fromEvent(event);
        bookQueryRepository.save(bookQueryEntity);
    }

    @JmsListener(destination = "StockDecreasedTopic")
    void handleStockDecreasedEvent(StockDecreasedEvent event) {
        var bookQueryEntity = bookQueryRepository
            .findById(event.aggregateId().id())
            .orElseThrow(
                () ->
                    new IllegalArgumentException(
                        String.format(
                            "Book with id %s does not exist.",
                            event.aggregateId().id()
                        )
                    )
            );
        bookQueryEntity = bookQueryEntity.fromEvent(event);
        bookQueryRepository.save(bookQueryEntity);
    }

    @JmsListener(destination = "StockIncreasedTopic")
    void handleStockIncreasedEvent(StockIncreasedEvent event) {
        var bookQueryEntity = bookQueryRepository
            .findById(event.aggregateId().id())
            .orElseThrow(
                () ->
                    new IllegalArgumentException(
                        String.format(
                            "Book with id %s does not exist.",
                            event.aggregateId().id()
                        )
                    )
            );
        bookQueryEntity = bookQueryEntity.fromEvent(event);
        bookQueryRepository.save(bookQueryEntity);
    }
}
