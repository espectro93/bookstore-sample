package com.espectro93.examples.springgraphqlbookstore.infrastructure.config;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.github.couchmove.Couchmove;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CouchmoveConfig {

    private final CouchbaseProperties couchbaseProperties;

    @Bean
    public Couchmove couchmove() {
        Cluster cluster = Cluster.connect(
            couchbaseProperties.getHost(),
            couchbaseProperties.getUsername(),
            couchbaseProperties.getPassword()
        );
        Bucket bucket = cluster.bucket(couchbaseProperties.getBucket());

        var couchmove = new Couchmove(bucket, cluster);
        couchmove.migrate();
        return couchmove;
    }
}
