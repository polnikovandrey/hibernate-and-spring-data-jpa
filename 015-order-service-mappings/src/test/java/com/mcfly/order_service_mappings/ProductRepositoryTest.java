package com.mcfly.order_service_mappings;

import com.mcfly.order_service_mappings.domain.Product;
import com.mcfly.order_service_mappings.domain.ProductStatus;
import com.mcfly.order_service_mappings.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = "com.mcfly.order_service_mappings")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    void testSaveNewProduct() {
        final Product product = new Product("Test product", ProductStatus.NEW);
        final Product saved = productRepository.save(product);
        productRepository.flush();
        final Product found = productRepository.getReferenceById(saved.getId());
        assertThat(found).isNotNull();
        assertThat(found.getDescription()).isNotNull();
        assertThat(found.getProductStatus()).isNotNull();
        assertThat(found.getCreatedDate()).isNotNull();
        assertThat(found.getLastModifiedDate()).isNotNull();
    }
}
