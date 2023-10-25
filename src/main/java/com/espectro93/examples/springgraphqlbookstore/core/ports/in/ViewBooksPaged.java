package com.espectro93.examples.springgraphqlbookstore.core.ports.in;

import com.espectro93.examples.springgraphqlbookstore.core.application.book.ViewBooksPagedQuery;
import com.espectro93.examples.springgraphqlbookstore.core.domain.book.BookView;
import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.UseCase;
import org.springframework.data.domain.Page;

public interface ViewBooksPaged
    extends UseCase<ViewBooksPagedQuery, Page<BookView>> {}
