package com.mcfly.creditcard.repositories;

import com.mcfly.creditcard.domain.CreditCard;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("local")
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CreditCardRepositoryTest {

    private static final String CREDIT_CARD_NUMBER = "123456789000000";

    @Autowired
    CreditCardRepository creditCardRepository;

    @Test
    void testSaveAndStoreCreditCard() {
        final CreditCard creditCard = new CreditCard();
        creditCard.setCreditCardNumber(CREDIT_CARD_NUMBER);
        creditCard.setCvv("123");
        creditCard.setExpirationDate("12/2028");
        final CreditCard savedCreditCard = creditCardRepository.saveAndFlush(creditCard);
        System.out.println("### Getting CC from db");
        final CreditCard foundCreditCard = creditCardRepository.findById(savedCreditCard.getId()).orElseThrow(EntityNotFoundException::new);
        assertThat(savedCreditCard.getCreditCardNumber()).isEqualTo(foundCreditCard.getCreditCardNumber());
    }

}