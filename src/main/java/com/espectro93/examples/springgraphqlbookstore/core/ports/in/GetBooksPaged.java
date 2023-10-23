package com.espectro93.examples.springgraphqlbookstore.core.ports.in;

import com.espectro93.examples.springgraphqlbookstore.core.application.GetBooksPagedCommand;
import com.espectro93.examples.springgraphqlbookstore.core.domain.book.Book;
import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.UseCase;
import org.springframework.data.domain.Page;

public interface GetBooksPaged
    extends UseCase<GetBooksPagedCommand, Page<Book>> {}
