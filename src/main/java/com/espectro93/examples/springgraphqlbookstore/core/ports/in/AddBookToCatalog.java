package com.espectro93.examples.springgraphqlbookstore.core.ports.in;

import com.espectro93.examples.springgraphqlbookstore.core.application.book.AddBookToCatalogCommand;
import com.espectro93.examples.springgraphqlbookstore.core.domain.book.Book;
import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.UseCase;

public interface AddBookToCatalog
    extends UseCase<AddBookToCatalogCommand, Book> {}
