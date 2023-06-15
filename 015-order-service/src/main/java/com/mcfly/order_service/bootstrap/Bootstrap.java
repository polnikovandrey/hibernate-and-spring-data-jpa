package com.mcfly.order_service.bootstrap;

import com.mcfly.order_service.domain.OrderHeader;
import com.mcfly.order_service.repository.OrderHeaderRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class Bootstrap implements CommandLineRunner {

    public static final Logger LOGGER = Logger.getLogger(Bootstrap.class.getSimpleName());

    private final OrderHeaderRepository orderHeaderRepository;

    public Bootstrap(OrderHeaderRepository orderHeaderRepository) {
        this.orderHeaderRepository = orderHeaderRepository;
    }

    @Transactional      // Without @Transactional orderLine.getProduct().getCategories() will fail.
    @Override
    public void run(String... args) {
        final OrderHeader orderHeader = orderHeaderRepository.findById(1L).orElseThrow(EntityNotFoundException::new);
        orderHeader.getOrderLines()
                   .forEach(orderLine -> {
                       LOGGER.info(orderLine.getProduct().getDescription());
                       orderLine.getProduct()
                                .getCategories()
                                .forEach(category -> LOGGER.info(category.getDescription()));
                   });
    }
}
