package com.mcfly.creditcard.repositories.creditcard;

import com.mcfly.creditcard.domain.creditcard.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
}
