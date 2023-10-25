package com.espectro93.examples.springgraphqlbookstore.core.application.book;

import com.espectro93.examples.springgraphqlbookstore.core.domain.book.BookId;
import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.Command;
import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.Query;

public record ViewBookQuery(BookId bookId) implements Query {}
