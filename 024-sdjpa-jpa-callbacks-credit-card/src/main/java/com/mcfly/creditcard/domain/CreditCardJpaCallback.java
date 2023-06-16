package com.mcfly.creditcard.domain;

import jakarta.persistence.*;

public class CreditCardJpaCallback {

    @PrePersist
    @PreUpdate
    public void beforeInsertOrUpdate(CreditCard creditCard) {
        System.out.println("$$$ before insert or update was called");
    }

    @PostPersist
    @PostLoad
    @PostUpdate
    public void postLoad(CreditCard creditCard) {
        System.out.println("$$$ post load was called");
    }
}
