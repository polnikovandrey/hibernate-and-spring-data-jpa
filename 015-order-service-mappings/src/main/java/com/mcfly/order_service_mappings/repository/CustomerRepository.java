package com.mcfly.order_service_mappings.repository;

import com.mcfly.order_service_mappings.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
