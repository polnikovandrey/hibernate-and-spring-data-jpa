package com.mcfly.creditcard.converters;

import com.mcfly.creditcard.config.SpringContextHelper;
import com.mcfly.creditcard.services.EncryptionService;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class CvvConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String attribute) {
        final String encrypted = getEncryptionService().encrypt(attribute);
        System.out.println("$$$ CvvConverter encrypted attribute [" + attribute + "] to [" + encrypted + "]");
        return encrypted;
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        final String decrypted = getEncryptionService().decrypt(dbData);
        System.out.println("$$$ CvvConverter decrypted dbData [" + dbData + "] to [" + decrypted + "]");
        return decrypted;
    }

    private EncryptionService getEncryptionService() {
        return SpringContextHelper.getApplicationContext().getBean(EncryptionService.class);
    }
}
