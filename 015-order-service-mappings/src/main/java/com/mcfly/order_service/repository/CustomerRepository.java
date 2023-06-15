package com.mcfly.order_service.repository;

import com.mcfly.order_service.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findCustomerByCustomerNameIgnoreCase(String customerName);
}
