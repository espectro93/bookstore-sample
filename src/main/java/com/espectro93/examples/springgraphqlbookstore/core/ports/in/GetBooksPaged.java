package com.espectro93.examples.springgraphqlbookstore.core.ports.in;

import com.espectro93.examples.springgraphqlbookstore.core.application.BookView;
import com.espectro93.examples.springgraphqlbookstore.core.application.GetBooksPagedCommand;
import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.UseCase;
import java.util.List;

public interface GetBooksPaged
    extends UseCase<GetBooksPagedCommand, List<BookView>> {}
