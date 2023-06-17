package com.mcfly.creditcard.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CardDbConfiguration {

    @Bean
    @ConfigurationProperties("spring.card.datasource")
    public DataSourceProperties cardDataSourceProperties() {
        return new DataSourceProperties();
    }
}
