package com.espectro93.examples.springgraphqlbookstore.core.application;

import com.espectro93.examples.springgraphqlbookstore.core.domain.Book;
import com.espectro93.examples.springgraphqlbookstore.core.ports.in.GetBooksPaged;
import com.espectro93.examples.springgraphqlbookstore.core.ports.out.LoadBooks;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class GetBooksPagedService implements GetBooksPaged {

    private final LoadBooks loadBooks;

    @Override
    public Page<Book> run(GetBooksPagedCommand input) {
        return loadBooks.loadByPageable(input.pageRequest());
    }
}
