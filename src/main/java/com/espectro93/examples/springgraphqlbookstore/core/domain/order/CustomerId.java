package com.espectro93.examples.springgraphqlbookstore.core.domain.order;

import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.Identifiable;

public record CustomerId(String id) implements Identifiable {}
