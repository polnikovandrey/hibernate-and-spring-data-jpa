package com.mcfly.order_service_mappings;

import com.mcfly.order_service_mappings.domain.*;
import com.mcfly.order_service_mappings.repository.CustomerRepository;
import com.mcfly.order_service_mappings.repository.OrderHeaderRepository;
import com.mcfly.order_service_mappings.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
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
public class OrderHeaderRepositoryTest {

    @Autowired
    OrderHeaderRepository orderHeaderRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CustomerRepository customerRepository;

    Product product;

    @BeforeEach
    void setUp() {
        final Product newProduct = new Product();
        newProduct.setProductStatus(ProductStatus.NEW);
        newProduct.setDescription("Test Product");
        product = productRepository.saveAndFlush(newProduct);
    }

    @Test
    void testSaveOrderWithLine() {
        final Address address = new Address("Test address", "Test city", "Test state", "Test zipCode");
        final Customer customer = new Customer("Andrey Polnikov", address, "789-123-456", "email@gmail.com");
        final Customer savedCustomer = customerRepository.save(customer);
        final OrderHeader orderHeader = new OrderHeader(savedCustomer);
        orderHeader.setShippingAddress(new Address("Test shipping address", "Test shipping city", "Test shipping state", "Test shipping zipCode"));
        orderHeader.setBillToAddress(new Address("Test billing address", "Test billing city", "Test billing state", "Test billing zipCode"));
        orderHeader.setOrderStatus(OrderStatus.NEW);
        final OrderLine orderLine = new OrderLine();
        orderLine.setQuantityOrdered(5);
        orderLine.setProduct(product);
        orderHeader.addOrderLine(orderLine);
        final OrderApproval orderApproval = new OrderApproval("me");
        orderHeader.setOrderApproval(orderApproval);
        final OrderHeader savedOrder = orderHeaderRepository.save(orderHeader);
        orderHeaderRepository.flush();
        assertThat(savedOrder).isNotNull();
        assertThat(savedOrder.getId()).isNotNull();
        assertThat(savedOrder.getOrderLines()).isNotNull();
        assertThat(savedOrder.getOrderLines().size()).isEqualTo(1);
        final OrderHeader fetchedOrder = orderHeaderRepository.getReferenceById(savedOrder.getId());
        assertThat(fetchedOrder).isNotNull();
        assertThat(fetchedOrder.getOrderLines().size()).isEqualTo(1);
        assertThat(fetchedOrder.getOrderApproval()).isNotNull();
        assertThat(fetchedOrder.getOrderApproval().getId()).isNotNull();
        assertThat(fetchedOrder.getOrderApproval().getApprovedBy()).isEqualTo(orderApproval.getApprovedBy());
    }

    @Test
    void testPersistNewOrderHeader() {
        final Address address = new Address("Test address", "Test city", "Test state", "Test zipCode");
        final Customer customer = new Customer("Andrey Polnikov", address, "789-123-456", "email@gmail.com");
        final Customer savedCustomer = customerRepository.save(customer);
        final OrderHeader orderHeader = new OrderHeader(savedCustomer);
        final OrderHeader saved = orderHeaderRepository.save(orderHeader);
        orderHeaderRepository.flush();
        final OrderHeader found = orderHeaderRepository.getReferenceById(saved.getId());
        assertThat(found).isNotNull();
        assertThat(found.getCreatedDate()).isNotNull();
        assertThat(found.getLastModifiedDate()).isNotNull();
    }

    @Test
    void testDeleteCascade() {
        final OrderHeader orderHeader = new OrderHeader();
        final Customer customer = new Customer();
        customer.setCustomerName("New customer");
        orderHeader.setCustomer(customer);
        final OrderLine orderLine = new OrderLine();
        orderLine.setQuantityOrdered(3);
        orderLine.setProduct(product);
        orderHeader.addOrderLine(orderLine);
        final OrderHeader savedOrder = orderHeaderRepository.saveAndFlush(orderHeader);
        System.out.println("Order saved and flushed");
        orderHeaderRepository.deleteById(savedOrder.getId());
        orderHeaderRepository.flush();
        final OrderHeader deletedNotFoundOrder = orderHeaderRepository.findById(savedOrder.getId()).orElse(null);
        assertThat(deletedNotFoundOrder).isNull();
    }

}
