package com.mcfly.order_service_mappings.domain;

import jakarta.persistence.Entity;

@Entity
public class OrderApproval extends BaseEntity {

    private String approvedBy;

    public OrderApproval() {
    }

    public OrderApproval(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }
}
