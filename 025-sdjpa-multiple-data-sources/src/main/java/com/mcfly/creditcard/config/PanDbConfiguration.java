package com.mcfly.creditcard.config;

import com.mcfly.creditcard.domain.pan.CreditCardPan;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = "com.mcfly.creditcard.repositories.pan", entityManagerFactoryRef = "panEntityManagerFactory", transactionManagerRef = "panTransactionManager")
public class PanDbConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties("spring.pan.datasource")
    public DataSourceProperties panDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.pan.datasource.hikari")
    @Primary
    public DataSource panDataSource(@Qualifier("panDataSourceProperties") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean panEntityManagerFactory(@Qualifier("panDataSource") DataSource dataSource, EntityManagerFactoryBuilder builder) {
        final Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.hbm2ddl.auto", "validate");
        jpaProperties.put("hibernate.physical_naming_strategy", "org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy");
        final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean
                = builder
                .dataSource(dataSource)
                .packages(CreditCardPan.class)
                .persistenceUnit("pan")
                .build();
        entityManagerFactoryBean.setJpaProperties(jpaProperties);
        return entityManagerFactoryBean;
    }

    @Bean
    @Primary
    public PlatformTransactionManager panTransactionManager(@Qualifier("panEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactoryBean.getObject()));
    }
}
