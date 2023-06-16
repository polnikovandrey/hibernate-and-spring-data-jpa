package com.mcfly.order_service.bootstrap;

import com.mcfly.order_service.domain.Customer;
import com.mcfly.order_service.domain.Product;
import com.mcfly.order_service.domain.ProductStatus;
import com.mcfly.order_service.repository.CustomerRepository;
import com.mcfly.order_service.services.BootstrapOrderService;
import com.mcfly.order_service.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final BootstrapOrderService bootstrapOrderService;
    private final CustomerRepository customerRepository;
    private final ProductService productService;

    @Autowired
    public Bootstrap(BootstrapOrderService bootstrapOrderService,
                     CustomerRepository customerRepository,
                     ProductService productService) {
        this.bootstrapOrderService = bootstrapOrderService;
        this.customerRepository = customerRepository;
        this.productService = productService;
    }

    @Override
    public void run(String... args) {
        bootstrapOrderService.readOrderData();
        performOptimisticLockingDemo();
        updateProduct();
    }

    private void performOptimisticLockingDemo() {
        final Customer customer = new Customer();

        customer.setCustomerName("Testing version");
        final Customer savedCustomer = customerRepository.save(customer);
        System.out.println("### version is: " + savedCustomer.getVersion());

        savedCustomer.setCustomerName("Testing version 2");
        final Customer savedCustomer2 = customerRepository.save(savedCustomer);
        System.out.println("### version is: " + savedCustomer2.getVersion());

        savedCustomer2.setCustomerName("Testing version 3");
        /*
            @Version Customer#version is checked before a save takes place.
            customerRepository.save(savedCustomer); will throw an exception - the actual version was changed already.
            @Version locking mechanism is being called "optimistic locking".
         */
        final Customer savedCustomer3 = customerRepository.save(savedCustomer2);
        System.out.println("### version is: " + savedCustomer3.getVersion());

        customerRepository.deleteById(savedCustomer3.getId());
    }

    private void updateProduct() {
        final Product product = new Product("Test product", ProductStatus.NEW);
        final Product savedProduct = productService.saveProduct(product);
        final Product savedProduct2 = productService.updateQualityOnHand(savedProduct.getId(), 25);
        System.out.println("Actual product quantity on hand: " + savedProduct2.getQuantityOnHand());
    }
}
