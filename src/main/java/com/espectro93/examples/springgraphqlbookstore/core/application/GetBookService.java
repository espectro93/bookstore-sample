package com.espectro93.examples.springgraphqlbookstore.core.application;

import com.espectro93.examples.springgraphqlbookstore.core.domain.Book;
import com.espectro93.examples.springgraphqlbookstore.core.ports.in.GetBook;
import com.espectro93.examples.springgraphqlbookstore.core.ports.out.LoadBook;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class GetBookService implements GetBook {

    private final LoadBook loadBook;

    @Override
    public Book run(GetBookCommand input) {
        return loadBook.loadBy(input.bookId());
    }
}
