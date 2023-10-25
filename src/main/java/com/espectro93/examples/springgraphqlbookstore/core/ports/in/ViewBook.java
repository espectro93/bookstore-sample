package com.espectro93.examples.springgraphqlbookstore.core.ports.in;

import com.espectro93.examples.springgraphqlbookstore.core.application.book.ViewBookQuery;
import com.espectro93.examples.springgraphqlbookstore.core.domain.book.BookView;
import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.UseCase;

public interface ViewBook extends UseCase<ViewBookQuery, BookView> {}
