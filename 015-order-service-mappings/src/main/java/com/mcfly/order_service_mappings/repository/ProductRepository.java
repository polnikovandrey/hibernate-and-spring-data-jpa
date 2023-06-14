package com.mcfly.order_service_mappings.repository;

import com.mcfly.order_service_mappings.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
