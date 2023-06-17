package com.mcfly.creditcard.repositories.cardholder;

import com.mcfly.creditcard.domain.cardholder.CreditCardHolder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardHolderRepository extends JpaRepository<CreditCardHolder, Long> {
}
