package com.espectro93.examples.springgraphqlbookstore.infrastructure.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "storage")
public class CouchbaseProperties {

    private String host;
    private String username;
    private String password;
    private String bucket;
}
