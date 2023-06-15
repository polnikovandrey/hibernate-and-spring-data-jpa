package com.mcfly.order_service.services;

import com.mcfly.order_service.domain.OrderHeader;
import com.mcfly.order_service.repository.OrderHeaderRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class BootstrapOrderService {

    private final OrderHeaderRepository orderHeaderRepository;

    public BootstrapOrderService(OrderHeaderRepository orderHeaderRepository) {
        this.orderHeaderRepository = orderHeaderRepository;
    }

    @Transactional
    public void readOrderData() {
        final OrderHeader orderHeader = orderHeaderRepository.findById(1L).orElseThrow(EntityNotFoundException::new);
        orderHeader.getOrderLines()
                   .forEach(orderLine -> {
                       printInfo(orderLine.getProduct().getDescription());
                       /*
                            orderLine.getProduct().getCategories() will fail without @Transactional on method:
                             field categories is LAZY, could be fetched within a hibernate session (transaction) only.
                        */
                       orderLine.getProduct()
                                .getCategories()
                                .forEach(category -> printInfo(category.getDescription()));
                   });
    }

    private void printInfo(String text) {
        System.out.println("### " + text);
    }
}
