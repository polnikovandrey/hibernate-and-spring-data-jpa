package com.mcfly.creditcard.repositories.pan;

import com.mcfly.creditcard.domain.pan.CreditCardPan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardPanRepository extends JpaRepository<CreditCardPan, Long> {
}
