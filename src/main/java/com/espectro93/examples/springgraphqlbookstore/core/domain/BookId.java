package com.espectro93.examples.springgraphqlbookstore.core.domain;

import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.Identifiable;

public record BookId(String id) implements Identifiable {}
