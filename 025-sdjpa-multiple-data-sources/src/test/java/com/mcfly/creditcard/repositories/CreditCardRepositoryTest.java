package com.mcfly.creditcard.repositories;

import com.mcfly.creditcard.domain.creditcard.CreditCard;
import com.mcfly.creditcard.services.EncryptionService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("local")
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CreditCardRepositoryTest {

    private static final String CREDIT_CARD_NUMBER = "123456789000000";

    @Autowired
    EncryptionService encryptionService;
    @Autowired
    CreditCardRepository creditCardRepository;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void testSaveAndStoreCreditCard() {
        final CreditCard creditCard = new CreditCard();
        creditCard.setCreditCardNumber(CREDIT_CARD_NUMBER);
        creditCard.setCvv("123");
        creditCard.setExpirationDate("12/2028");
        final CreditCard savedCreditCard = creditCardRepository.saveAndFlush(creditCard);
        System.out.println("### Getting CC from db: " + creditCard.getCreditCardNumber());
        System.out.println("### CC At Rest");
        System.out.println("### CC Encrypted: " + encryptionService.encrypt(CREDIT_CARD_NUMBER));

        final Map<String, Object> dbRow = jdbcTemplate.queryForMap("select * from credit_card where id = " + savedCreditCard.getId());
        final String dbCreditCardNumber = (String) dbRow.get("credit_card_number");
        assertThat(savedCreditCard.getCreditCardNumber()).isNotEqualTo(dbCreditCardNumber);
        assertThat(dbCreditCardNumber).isEqualTo(encryptionService.encrypt(CREDIT_CARD_NUMBER));

        final CreditCard foundCreditCard = creditCardRepository.findById(savedCreditCard.getId()).orElseThrow(EntityNotFoundException::new);
        assertThat(savedCreditCard.getCreditCardNumber()).isEqualTo(foundCreditCard.getCreditCardNumber());
    }

}