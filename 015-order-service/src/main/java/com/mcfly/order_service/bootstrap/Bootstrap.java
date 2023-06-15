package com.mcfly.order_service.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final BootstrapOrderService bootstrapOrderService;

    public Bootstrap(BootstrapOrderService bootstrapOrderService) {
        this.bootstrapOrderService = bootstrapOrderService;
    }

    @Override
    public void run(String... args) {
        bootstrapOrderService.readOrderData();
    }
}
