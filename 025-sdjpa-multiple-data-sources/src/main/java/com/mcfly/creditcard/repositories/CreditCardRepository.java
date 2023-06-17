package com.mcfly.creditcard.repositories;

import com.mcfly.creditcard.domain.creditcard.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jt on 6/27/22.
 */
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
}
