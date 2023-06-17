package com.mcfly.creditcard.repositories.pan;

import com.mcfly.creditcard.domain.pan.CreditCardPan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CreditCardPanRepository extends JpaRepository<CreditCardPan, Long> {

    Optional<CreditCardPan> findByCreditCardId(Long id);
}
