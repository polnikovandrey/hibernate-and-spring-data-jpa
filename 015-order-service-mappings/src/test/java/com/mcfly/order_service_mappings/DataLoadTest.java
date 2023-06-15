package com.mcfly.order_service_mappings;

import com.mcfly.order_service_mappings.domain.*;
import com.mcfly.order_service_mappings.repository.CustomerRepository;
import com.mcfly.order_service_mappings.repository.OrderHeaderRepository;
import com.mcfly.order_service_mappings.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DataLoadTest {

    private static final String PRODUCT_D1 = "Product 1";
    private static final String PRODUCT_D2 = "Product 2";
    private static final String PRODUCT_D3 = "Product 3";
    private static final String TEST_CUSTOMER = "Test customer";

    @Autowired
    OrderHeaderRepository orderHeaderRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;

    @Test
    void testDbLock() {
        // Use "select for update" query in manual commit mode. order_header table update will be blocked until query was not committed.
        // select * from order_header where id = 1 for update;
        final Long id = 1L;
        final OrderHeader orderHeader = orderHeaderRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        final Address billTo = new Address();
        billTo.setAddress("Bill me");
        orderHeader.setBillToAddress(billTo);
        orderHeaderRepository.saveAndFlush(orderHeader);
        System.out.println("I updated the order");
    }

    @Test
    void testLazyVsEager() {
        final OrderHeader orderHeader = orderHeaderRepository.getReferenceById(1L);
        System.out.println("Order id: " + orderHeader.getId());
        System.out.println("Customer name is: " + orderHeader.getCustomer().getCustomerName());
    }

    @Disabled
    @Commit
    @Test
    void testDataLoader() {
        final List<Product> products = loadProducts();
        final Customer customer = loadCustomers();
        IntStream.range(0, 10)
                .forEach(i -> {
                    System.out.println("Creating order #: " + i);
                    saveOrder(customer, products);
                });
        orderHeaderRepository.flush();
    }

    private OrderHeader saveOrder(Customer customer, List<Product> products) {
        final Random random = new Random();
        final OrderHeader orderHeader = new OrderHeader();
        orderHeader.setCustomer(customer);
        products.forEach(product -> {
            final OrderLine orderLine = new OrderLine();
            orderLine.setProduct(product);
            orderLine.setQuantityOrdered(random.nextInt(20));
            orderHeader.addOrderLine(orderLine);
        });
        return orderHeaderRepository.save(orderHeader);
    }


    private Customer loadCustomers() {
        return getOrSaveCustomer(TEST_CUSTOMER);
    }

    private Customer getOrSaveCustomer(String customerName) {
        return customerRepository.findCustomerByCustomerNameIgnoreCase(customerName)
                .orElseGet(() -> {
                    final Customer c1 = new Customer();
                    c1.setCustomerName(customerName);
                    c1.setEmail("test@example.com");
                    final Address address = new Address();
                    address.setAddress("123 Main");
                    address.setCity("New Orleans");
                    address.setState("LA");
                    c1.setAddress(address);
                    return customerRepository.save(c1);
                });
    }

    private List<Product> loadProducts() {
        final List<Product> products = new ArrayList<>();
        products.add(getOrSaveProduct(PRODUCT_D1));
        products.add(getOrSaveProduct(PRODUCT_D2));
        products.add(getOrSaveProduct(PRODUCT_D3));
        return products;
    }

    private Product getOrSaveProduct(String description) {
        return productRepository.findByDescription(description)
                .orElseGet(() -> {
                    final Product p1 = new Product();
                    p1.setDescription(description);
                    p1.setProductStatus(ProductStatus.NEW);
                    return productRepository.save(p1);
                });
    }
}
