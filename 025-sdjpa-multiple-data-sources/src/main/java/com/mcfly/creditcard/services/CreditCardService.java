package com.mcfly.creditcard.services;

import com.mcfly.creditcard.domain.creditcard.CreditCard;

public interface CreditCardService {

    CreditCard saveCreditCard(CreditCard creditCard);

    CreditCard getCreditCardById(Long id);
}
