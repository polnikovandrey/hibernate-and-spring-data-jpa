package com.mcfly.creditcard.repositories.cardholder;

import com.mcfly.creditcard.domain.cardholder.CreditCardHolder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CreditCardHolderRepository extends JpaRepository<CreditCardHolder, Long> {

    Optional<CreditCardHolder> findByCreditCardId(Long id);
}
