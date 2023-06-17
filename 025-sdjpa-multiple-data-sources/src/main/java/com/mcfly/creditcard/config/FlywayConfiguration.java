package com.mcfly.creditcard.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfiguration {

    @Bean
    @ConfigurationProperties("spring.card.flyway")
    public DataSourceProperties cardFlywayDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.cardholder.flyway")
    public DataSourceProperties cardholderFlywayDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.pan.flyway")
    public DataSourceProperties panFlywayDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(initMethod = "migrate")
    public Flyway cardFlyway(@Qualifier("cardFlywayDataSourceProperties") DataSourceProperties dataSourceProperties) {
        return Flyway
                .configure()
                .dataSource(
                        dataSourceProperties.getUrl(),
                        dataSourceProperties.getUsername(),
                        dataSourceProperties.getPassword())
                .locations("classpath:/db/migrations/card")
                .load();
    }

    @Bean(initMethod = "migrate")
    public Flyway cardholderFlyway(@Qualifier("cardholderFlywayDataSourceProperties") DataSourceProperties dataSourceProperties) {
        return Flyway
                .configure()
                .dataSource(
                        dataSourceProperties.getUrl(),
                        dataSourceProperties.getUsername(),
                        dataSourceProperties.getPassword())
                .locations("classpath:/db/migrations/cardholder")
                .load();
    }

    @Bean(initMethod = "migrate")
    public Flyway panFlyway(@Qualifier("panFlywayDataSourceProperties") DataSourceProperties dataSourceProperties) {
        return Flyway
                .configure()
                .dataSource(
                        dataSourceProperties.getUrl(),
                        dataSourceProperties.getUsername(),
                        dataSourceProperties.getPassword())
                .locations("classpath:/db/migrations/pan")
                .load();
    }

}
