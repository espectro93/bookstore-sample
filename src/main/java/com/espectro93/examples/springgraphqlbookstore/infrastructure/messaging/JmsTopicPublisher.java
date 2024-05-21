package com.espectro93.examples.springgraphqlbookstore.infrastructure.messaging;

import com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence.outbox.OutboxEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsTopicPublisher {

    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;

    JmsTopicPublisher(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
        jmsTemplate.setPubSubDomain(true);
        this.objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());
    }

    @SneakyThrows
    public void sendMessage(String topic, OutboxEntity entity) {
        jmsTemplate.convertAndSend(
            topic,
            objectMapper.writeValueAsString(entity.event())
        );
    }
}
