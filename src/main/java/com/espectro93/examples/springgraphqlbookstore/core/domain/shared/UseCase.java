package com.espectro93.examples.springgraphqlbookstore.core.domain.shared;

public interface UseCase<T extends Command, R> {
    R run(T input);
}
