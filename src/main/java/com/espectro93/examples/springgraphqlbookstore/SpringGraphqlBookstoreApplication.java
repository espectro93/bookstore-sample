package com.espectro93.examples.springgraphqlbookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

@SpringBootApplication
public class SpringGraphqlBookstoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringGraphqlBookstoreApplication.class, args);
    }
}
