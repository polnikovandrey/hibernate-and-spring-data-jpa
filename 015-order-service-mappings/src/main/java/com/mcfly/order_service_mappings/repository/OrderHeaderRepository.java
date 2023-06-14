package com.mcfly.order_service_mappings.repository;

import com.mcfly.order_service_mappings.domain.OrderHeader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Long> {
}
