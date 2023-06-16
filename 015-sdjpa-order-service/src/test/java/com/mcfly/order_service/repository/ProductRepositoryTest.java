package com.mcfly.order_service.repository;

import com.mcfly.order_service.domain.Product;
import com.mcfly.order_service.domain.ProductStatus;
import com.mcfly.order_service.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackageClasses = {ProductService.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductService productService;

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
        final Product savedProduct = productService.saveProduct(product);
        final Product savedProduct2 = productService.updateQualityOnHand(savedProduct.getId(), 25);
        assertThat(savedProduct2.getQuantityOnHand()).isEqualTo(25);
        System.out.println("Actual product quantity on hand: " + savedProduct2.getQuantityOnHand());
    }
}
