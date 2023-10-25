package com.espectro93.examples.springgraphqlbookstore.core.domain.shared;

public sealed interface Operation permits Command, Query{
}
