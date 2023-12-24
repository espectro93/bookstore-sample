package com.espectro93.examples.springgraphqlbookstore.infrastructure.messaging;

import jakarta.jms.Topic;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JmsTopics {

    @Bean
    public Topic bookAddedToCatalogTopic() {
        return new ActiveMQTopic("BookAddedToCatalogTopic");
    }

    @Bean
    public Topic orderPlacedTopic() {
        return new ActiveMQTopic("OrderPlacedTopic");
    }

    @Bean
    public Topic orderCancelledTopic() {
        return new ActiveMQTopic("OrderCancelledTopic");
    }

    @Bean
    public Topic stockIncreasedTopic() {
        return new ActiveMQTopic("StockIncreasedTopic");
    }

    @Bean
    public Topic stockDecreasedTopic() {
        return new ActiveMQTopic("StockDecreasedTopic");
    }

    @Bean
    public Topic bookAddedToCatalogDeadLetterTopic() {
        return new ActiveMQTopic("BookAddedToCatalogTopic.DLQ");
    }

    @Bean
    public Topic orderPlacedDeadLetterTopic() {
        return new ActiveMQTopic("OrderPlacedTopic.DLQ");
    }

    @Bean
    public Topic orderCancelledDeadLetterTopic() {
        return new ActiveMQTopic("OrderCancelledTopic.DLQ");
    }

    @Bean
    public Topic stockIncreasedDeadLetterTopic() {
        return new ActiveMQTopic("StockIncreasedTopic.DLQ");
    }

    @Bean
    public Topic stockDecreasedDeadLetterTopic() {
        return new ActiveMQTopic("StockDecreasedTopic.DLQ");
    }
}
