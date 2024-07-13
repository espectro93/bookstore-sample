package com.espectro93.examples.springgraphqlbookstore.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.jms.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;

@Configuration
@EnableJms
@ComponentScan(basePackages = "com.espectro93.examples.springgraphqlbookstore")
public class JmsConfig {

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(
        ConnectionFactory connectionFactory
    ) {
        DefaultJmsListenerContainerFactory factory =
            new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(true); // Enable pub-sub
        factory.setMessageConverter(messageConverter());

        factory.setSessionTransacted(true);

        return factory;
    }

    @Bean
    public MessageConverter messageConverter() {
        MappingJackson2MessageConverter converter =
            new MappingJackson2MessageConverter();
        converter.setTypeIdPropertyName("_type");
        converter.setObjectMapper(
            new ObjectMapper().registerModule(new JavaTimeModule())
        );
        return converter;
    }
}
