package com.mcfly.creditcard.listeners;

import com.mcfly.creditcard.services.EncryptionService;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.springframework.stereotype.Component;

@Component
public class PreInsertListener extends AbstractEncryptionListener implements PreInsertEventListener {

    public PreInsertListener(EncryptionService encryptionService) {
        super(encryptionService);
    }

    @Override
    public boolean onPreInsert(PreInsertEvent event) {
        System.out.println("$$$ onPreInsert()");
        encrypt(event.getState(), event.getPersister().getPropertyNames(), event.getEntity());
        return false;
    }
}
