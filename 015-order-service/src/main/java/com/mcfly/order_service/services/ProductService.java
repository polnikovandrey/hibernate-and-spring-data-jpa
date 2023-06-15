package com.mcfly.order_service.services;

import com.mcfly.order_service.domain.Product;

public interface ProductService {

    Product saveProduct(Product product);

    Product updateQualityOnHand(Long id, Integer qualityOnHand);
}
