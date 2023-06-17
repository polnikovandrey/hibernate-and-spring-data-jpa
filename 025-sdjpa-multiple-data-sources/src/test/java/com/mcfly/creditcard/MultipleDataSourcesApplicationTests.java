package com.mcfly.creditcard;

import com.mcfly.creditcard.domain.creditcard.CreditCard;
import com.mcfly.creditcard.services.CreditCardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MultipleDataSourcesApplicationTests {

    @Autowired
    CreditCardService creditCardService;

    @Test
    void testSaveAndGetCreditCard() {
        final CreditCard creditCard
                = CreditCard.builder()
                .firstName("John")
                .lastName("Thompson")
                .zipCode("12345")
                .creditCardNumber("1234567")
                .cvv("123")
                .expirationDate("12/26")
                .build();
        final CreditCard savedCreditCard = creditCardService.saveCreditCard(creditCard);
        assertThat(savedCreditCard).isNotNull();
        assertThat(savedCreditCard.getId()).isNotNull();
        assertThat(savedCreditCard.getCreditCardNumber()).isNotNull();
    }

    @Test
    void contextLoads() {
    }

}
