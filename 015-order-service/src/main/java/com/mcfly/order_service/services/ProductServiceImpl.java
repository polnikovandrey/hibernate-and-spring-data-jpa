package com.mcfly.order_service.services;

import com.mcfly.order_service.domain.Product;
import com.mcfly.order_service.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.saveAndFlush(product);
    }

    @Override
    public Product updateQualityOnHand(Long id, Integer qualityOnHand) {
        final Product found = productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        found.setQuantityOnHand(qualityOnHand);
        return productRepository.saveAndFlush(found);
    }
}
