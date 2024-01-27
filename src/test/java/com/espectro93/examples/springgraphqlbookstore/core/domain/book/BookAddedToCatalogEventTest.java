package com.espectro93.examples.springgraphqlbookstore.core.domain.book;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BookAddedToCatalogEventTest {

    @Test
    void shouldSerialize() {
        var payload = "{\"eventId\":\"761842a6-18f8-464e-89b3-b7921887e65f\",\"eventTime\":1706388020.193000000,\"aggregateId\":{\"id\":\"5bb30ada-4f06-4465-880e-58a6c1f3ce56\"},\"eventType\":\"BookAddedToCatalog\",\"title\":\"Example Book Title\",\"authors\":[\"Author One\",\"Author Two\"],\"publishDate\":\"2023-01-01\",\"pages\":300,\"isbn\":\"123-456-7890\",\"publisherName\":\"Example Publisher\",\"stock\":100}";
        var mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        assertDoesNotThrow(() -> mapper.readValue(payload, BookAddedToCatalogEvent.class));
    }
}