package com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence;

import com.espectro93.examples.springgraphqlbookstore.core.domain.Book;
import com.espectro93.examples.springgraphqlbookstore.core.domain.BookId;
import com.espectro93.examples.springgraphqlbookstore.core.ports.out.LoadBook;
import com.espectro93.examples.springgraphqlbookstore.core.ports.out.LoadBooks;
import com.espectro93.examples.springgraphqlbookstore.infrastructure.error.EntityNotFoundException;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BookPersistenceAdapter implements LoadBook, LoadBooks {

    private final BookRepository bookRepository;

    @Override
    public Book loadBy(BookId bookId) {
        return BookEntity.toDomain(
            bookRepository
                .findById(bookId.id())
                .orElseThrow(EntityNotFoundException::new)
        );
    }

    @Override
    public Page<Book> loadByPageable(Pageable pageable) {
        return bookRepository.findAll(pageable).map(BookEntity::toDomain);
    }

    @Override
    public List<Book> loadByIds(List<BookId> bookIds) {
        return bookRepository
            .findAllById(bookIds.stream().map(BookId::id).toList())
            .stream()
            .map(BookEntity::toDomain)
            .toList();
    }
}
