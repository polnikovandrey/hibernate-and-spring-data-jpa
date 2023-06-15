package com.mcfly.order_service.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    @Override
    public void run(String... args) {
        System.out.println("I was called!");
    }
}
