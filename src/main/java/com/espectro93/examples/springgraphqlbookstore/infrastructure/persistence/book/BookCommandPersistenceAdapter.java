package com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence.book;

import com.espectro93.examples.springgraphqlbookstore.core.domain.book.Book;
import com.espectro93.examples.springgraphqlbookstore.core.domain.book.BookId;
import com.espectro93.examples.springgraphqlbookstore.core.ports.out.BookCommandPort;
import com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence.DomainEventEntity;
import com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence.DomainEventRepository;
import com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence.outbox.OutboxEntity;
import com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence.outbox.OutboxRepository;
import java.util.Collections;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class BookCommandPersistenceAdapter implements BookCommandPort {

    private final DomainEventRepository domainEventRepository;
    private final OutboxRepository outboxRepository;

    @Override
    @Transactional
    public void save(Book book) {
        domainEventRepository.saveAll(
            book
                .getUncommittedEvents()
                .stream()
                .map(DomainEventEntity::createFrom)
                .toList()
        );
        outboxRepository.saveAll(
            book
                .getUncommittedEvents()
                .stream()
                .map(OutboxEntity::createFrom)
                .toList()
        );
    }

    @Override
    public Book loadBy(BookId bookId) {
        var events = domainEventRepository.findAllById(
            Collections.singleton(bookId.id())
        );
        return Book.rehydrate(
            events.stream().map(DomainEventEntity::event).toList()
        );
    }
}
