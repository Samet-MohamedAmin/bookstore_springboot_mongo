package com.shared.configuration;

import com.shared.event.CascadeSaveMongoEventListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig{
    @Bean
    public CascadeSaveMongoEventListener userCascadingMongoEventListener() {
        return new CascadeSaveMongoEventListener();
    }
}