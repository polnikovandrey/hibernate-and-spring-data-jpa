package com.mcfly.creditcard.config;

import com.mcfly.creditcard.interceptors.EncryptionInterceptor;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class InterceptorRegistration implements HibernatePropertiesCustomizer {

    private final EncryptionInterceptor encryptionInterceptor;

    public InterceptorRegistration(EncryptionInterceptor encryptionInterceptor) {
        this.encryptionInterceptor = encryptionInterceptor;
    }

    @Override
    public void customize(Map<String, Object> hibernateProperties) {
        hibernateProperties.put("hibernate.session_factory.interceptor", encryptionInterceptor);
    }
}
