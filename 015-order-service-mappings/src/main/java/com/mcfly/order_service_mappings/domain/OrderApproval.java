package com.mcfly.order_service_mappings.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class OrderApproval extends BaseEntity {

    private String approvedBy;
    @OneToOne
    @JoinColumn(name = "order_header_id")
    private OrderHeader orderHeader;

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

    public OrderHeader getOrderHeader() {
        return orderHeader;
    }

    public void setOrderHeader(OrderHeader orderHeader) {
        this.orderHeader = orderHeader;
    }
}
