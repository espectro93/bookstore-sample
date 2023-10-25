package com.espectro93.examples.springgraphqlbookstore.core.application.book;

import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.Command;
import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.Query;
import org.springframework.data.domain.PageRequest;

public record ViewBooksPagedQuery(PageRequest pageRequest)
    implements Query {}
