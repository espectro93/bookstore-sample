package com.espectro93.examples.springgraphqlbookstore.core.application;

import com.espectro93.examples.springgraphqlbookstore.core.domain.BookId;
import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.Command;

public record GetBookCommand(BookId bookId) implements Command {}
