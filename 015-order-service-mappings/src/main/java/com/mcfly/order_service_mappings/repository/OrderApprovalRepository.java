package com.mcfly.order_service_mappings.repository;

import com.mcfly.order_service_mappings.domain.OrderApproval;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderApprovalRepository extends JpaRepository<OrderApproval, Long> {
}
