package com.mcfly.creditcard.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CardHolderDbConfiguration {

    @Bean
    @ConfigurationProperties("spring.cardholder.datasource")
    public DataSourceProperties cardHolderDataSourceProperties() {
        return new DataSourceProperties();
    }
}
