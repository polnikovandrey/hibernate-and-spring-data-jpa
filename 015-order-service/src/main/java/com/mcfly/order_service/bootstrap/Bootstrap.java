package com.mcfly.order_service.bootstrap;

import com.mcfly.order_service.domain.Customer;
import com.mcfly.order_service.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final BootstrapOrderService bootstrapOrderService;
    private final CustomerRepository customerRepository;

    @Autowired
    public Bootstrap(BootstrapOrderService bootstrapOrderService,
                     CustomerRepository customerRepository) {
        this.bootstrapOrderService = bootstrapOrderService;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) {
        bootstrapOrderService.readOrderData();

        final Customer customer = new Customer();
        customer.setCustomerName("Testing version");
        final Customer savedCustomer = customerRepository.save(customer);
        System.out.println("### version is: " + savedCustomer.getVersion());
        customerRepository.deleteById(savedCustomer.getId());
    }
}
