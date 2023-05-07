package com.espectro93.examples.springgraphqlbookstore.infrastructure.config;

import com.couchbase.client.java.Bucket;
import com.github.couchmove.Couchmove;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

@Slf4j
@Configuration
@AutoConfigureAfter(CouchbaseProperties.class)
@EnableCouchbaseRepositories(
    basePackages = {
        "com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence",
    }
)
public class CouchbaseConfig extends AbstractCouchbaseConfiguration {

    @Autowired
    private CouchbaseProperties properties;

    @Override
    public String getConnectionString() {
        return properties.getHost();
    }

    @Override
    public String getUserName() {
        return properties.getUsername();
    }

    @Override
    public String getPassword() {
        return properties.getPassword();
    }

    @Override
    public String getBucketName() {
        return properties.getBucket();
    }
}
