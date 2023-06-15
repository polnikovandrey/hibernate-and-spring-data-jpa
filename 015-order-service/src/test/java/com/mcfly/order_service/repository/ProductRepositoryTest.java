package com.mcfly.order_service.repository;

import com.mcfly.order_service.domain.Product;
import com.mcfly.order_service.domain.ProductStatus;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = "com.mcfly.order_service")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    void testSaveNewProduct() {
        final Product product = new Product("Test product", ProductStatus.NEW);
        final Product savedProduct = productRepository.save(product);
        productRepository.flush();
        final Product found = productRepository.getReferenceById(savedProduct.getId());
        assertThat(found).isNotNull();
        assertThat(found.getDescription()).isNotNull();
        assertThat(found.getProductStatus()).isNotNull();
        assertThat(found.getCreatedDate()).isNotNull();
        assertThat(found.getLastModifiedDate()).isNotNull();
    }

    @Test
    void testGetCategory() {
        final Product product = productRepository.findByDescription("PRODUCT1").orElse(null);
        assertThat(product).isNotNull();
        assertThat(product.getCategories()).isNotNull();
    }

    @Test
    void testSetAndUpdateProductQuantityOnHand() {
        final Product product = new Product("Test product", ProductStatus.NEW);
        final Product savedProduct = productRepository.save(product);
        savedProduct.setQuantityOnHand(15);
        final Product productWithNewQuantity = productRepository.save(savedProduct);
        assertThat(productWithNewQuantity.getQuantityOnHand()).isEqualTo(15);
        productWithNewQuantity.setQuantityOnHand(20);
        final Product productWithUpdatedQuantity = productRepository.save(productWithNewQuantity);
        productRepository.flush();
        final Product actualProduct
                = productRepository.findById(productWithUpdatedQuantity.getId())
                                   .orElseThrow(EntityNotFoundException::new);
        assertThat(actualProduct.getQuantityOnHand()).isEqualTo(20);
    }
}
