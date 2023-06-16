package com.mcfly.sdjpainheritance.domain.mapped_super_class;

import jakarta.persistence.Entity;

@Entity
public class OrderHeader extends BaseEntity {

    private String customerName;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
