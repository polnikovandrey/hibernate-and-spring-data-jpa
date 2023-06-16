package com.mcfly.creditcard.listeners;

import com.mcfly.creditcard.domain.EncryptedString;
import com.mcfly.creditcard.services.EncryptionService;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

public class AbstractEncryptionListener {

    private final EncryptionService encryptionService;

    public AbstractEncryptionListener(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    public void encrypt(Object[] state, String[] propertyNames, Object entity) {
        ReflectionUtils.doWithFields(entity.getClass(), field -> encryptField(field, state, propertyNames), this::isFieldEncrypted);
    }

    private void encryptField(Field field, Object[] state, String[] propertyNames) {
        final int idx = getPropertyIndex(field.getName(), propertyNames);
        final Object currentValue = state[idx];
        state[idx] = encryptionService.encrypt(currentValue.toString());
    }

    public void decrypt(Object entity) {
        ReflectionUtils.doWithFields(entity.getClass(), field -> decryptField(field, entity), this::isFieldEncrypted);
    }

    private void decryptField(Field field, Object entity) {
        field.setAccessible(true);
        final Object value = ReflectionUtils.getField(field, entity);
        ReflectionUtils.setField(field, entity, encryptionService.decrypt(value.toString()));
    }

    public boolean isFieldEncrypted(Field field) {
        return AnnotationUtils.findAnnotation(field, EncryptedString.class) != null;
    }

    public int getPropertyIndex(String name, String[] properties) {
        for (int i = 0; i < properties.length; i++) {
            if (name.equals(properties[i])) {
                return i;
            }
        }
        // Should never get here.
        throw new IllegalArgumentException("Property not found: " + name);
    }
}
