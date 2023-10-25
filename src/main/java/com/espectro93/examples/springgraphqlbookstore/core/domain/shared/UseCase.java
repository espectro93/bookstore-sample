package com.espectro93.examples.springgraphqlbookstore.core.domain.shared;

public interface UseCase<T extends Operation , R> {
    R run(T input);
}
