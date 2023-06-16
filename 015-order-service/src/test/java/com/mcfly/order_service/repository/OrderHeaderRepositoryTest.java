package com.mcfly.order_service.repository;

import com.mcfly.order_service.domain.*;
import jakarta.validation.ConstraintViolationException;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"com.mcfly.oder_service"})
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
    void testCustomerBeanValidationConstraints() {
        final Supplier<Customer> validCustomerSupplier = () -> {
            final Address validAddress = new Address("Test address", "Test city", "Test state", "Test zipCode");
            return new Customer("Andrey Polnikov", validAddress, "789-123-456", "email@gmail.com");
        };
        final Customer validCustomer = validCustomerSupplier.get();
        final Customer savedCustomer = customerRepository.save(validCustomer);
        assertThat(savedCustomer).isNotNull();

        final Customer customer1 = validCustomerSupplier.get();
        customer1.setCustomerName(RandomStringUtils.randomAlphabetic(51));
        assertThrows(ConstraintViolationException.class, () -> customerRepository.save(customer1));

        final Customer customer2 = validCustomerSupplier.get();
        customer2.setPhone(RandomStringUtils.randomAlphabetic(21));
        assertThrows(ConstraintViolationException.class, () -> customerRepository.save(customer2));

        final Customer customer3 = validCustomerSupplier.get();
        customer3.setEmail("Not valid email pattern");
        assertThrows(ConstraintViolationException.class, () -> customerRepository.save(customer3));

        final Customer customer4 = validCustomerSupplier.get();
        customer4.setEmail(RandomStringUtils.randomAlphabetic(200) + "@" + RandomStringUtils.randomAlphabetic(50) + "." + RandomStringUtils.randomAlphabetic(4));
        assertThrows(ConstraintViolationException.class, () -> customerRepository.save(customer4));

        final Customer customer5 = validCustomerSupplier.get();
        customer5.getAddress().setAddress(RandomStringUtils.randomAlphabetic(31));
        assertThrows(ConstraintViolationException.class, () -> customerRepository.save(customer5));

        final Customer customer6 = validCustomerSupplier.get();
        customer6.getAddress().setCity(RandomStringUtils.randomAlphabetic(31));
        assertThrows(ConstraintViolationException.class, () -> customerRepository.save(customer6));

        final Customer customer7 = validCustomerSupplier.get();
        customer7.getAddress().setState(RandomStringUtils.randomAlphabetic(31));
        assertThrows(ConstraintViolationException.class, () -> customerRepository.save(customer7));

        final Customer customer8 = validCustomerSupplier.get();
        customer8.getAddress().setZipCode(RandomStringUtils.randomAlphabetic(31));
        assertThrows(ConstraintViolationException.class, () -> customerRepository.save(customer8));
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

        final OrderApproval orderApproval = new OrderApproval("me");
        orderHeader.setOrderApproval(orderApproval);

        orderHeader.addOrderLine(orderLine);
        final OrderHeader savedOrder = orderHeaderRepository.saveAndFlush(orderHeader);
        System.out.println("Order saved and flushed");
        orderHeaderRepository.deleteById(savedOrder.getId());
        orderHeaderRepository.flush();
        final OrderHeader deletedNotFoundOrder = orderHeaderRepository.findById(savedOrder.getId()).orElse(null);
        assertThat(deletedNotFoundOrder).isNull();
    }

}
