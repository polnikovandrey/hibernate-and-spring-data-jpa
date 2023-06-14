package com.mcfly.order_service_mappings;

import com.mcfly.order_service_mappings.domain.OrderHeader;
import com.mcfly.order_service_mappings.repository.OrderHeaderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"com.mcfly.oder_service_mappings"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderRepositoryTest {

    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    @Test
    void testPersistNewOrderHeader() {
        final OrderHeader orderHeader = new OrderHeader("Andrey Polnikov");
        final OrderHeader saved = orderHeaderRepository.save(orderHeader);
        orderHeaderRepository.flush();
        final OrderHeader found = orderHeaderRepository.getReferenceById(saved.getId());
        assertThat(found).isNotNull();
        assertThat(found.getCreatedDate()).isNotNull();
    }

}
