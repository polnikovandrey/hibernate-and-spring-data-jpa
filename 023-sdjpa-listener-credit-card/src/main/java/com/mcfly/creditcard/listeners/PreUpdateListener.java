package com.mcfly.creditcard.listeners;

import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;
import org.springframework.stereotype.Component;

@Component
public class PreUpdateListener implements PreUpdateEventListener {

    @Override
    public boolean onPreUpdate(PreUpdateEvent event) {
        System.out.println("$$$ onPreUpdate()");
        return false;
    }
}
