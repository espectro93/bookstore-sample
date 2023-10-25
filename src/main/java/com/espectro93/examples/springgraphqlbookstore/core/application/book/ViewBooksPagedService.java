package com.espectro93.examples.springgraphqlbookstore.core.application.book;

import com.espectro93.examples.springgraphqlbookstore.core.domain.book.BookView;
import com.espectro93.examples.springgraphqlbookstore.core.ports.in.ViewBooksPaged;
import com.espectro93.examples.springgraphqlbookstore.core.ports.out.BookQueryPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class ViewBooksPagedService implements ViewBooksPaged {

    private final BookQueryPort bookQueryPort;

    @Override
    public Page<BookView> run(ViewBooksPagedQuery input) {
        return bookQueryPort.loadByPageable(input.pageRequest());
    }
}
