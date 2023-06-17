package com.mcfly.creditcard.services;

import com.mcfly.creditcard.domain.cardholder.CreditCardHolder;
import com.mcfly.creditcard.domain.creditcard.CreditCard;
import com.mcfly.creditcard.domain.pan.CreditCardPan;
import com.mcfly.creditcard.repositories.cardholder.CreditCardHolderRepository;
import com.mcfly.creditcard.repositories.creditcard.CreditCardRepository;
import com.mcfly.creditcard.repositories.pan.CreditCardPanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreditCardServiceImpl implements CreditCardService {

    private final CreditCardHolderRepository creditCardHolderRepository;
    private final CreditCardRepository creditCardRepository;
    private final CreditCardPanRepository creditCardPanRepository;

    @Transactional
    @Override
    public CreditCard saveCreditCard(CreditCard creditCard) {
        final CreditCard savedCreditCard = creditCardRepository.save(creditCard);

        // Repopulating transient properties with the original object values.
        savedCreditCard.setFirstName(creditCard.getFirstName());
        savedCreditCard.setLastName(creditCard.getLastName());
        savedCreditCard.setZipCode(creditCard.getZipCode());
        savedCreditCard.setCreditCardNumber(creditCard.getCreditCardNumber());

        // Applying changes to other data sources.
        creditCardHolderRepository.save(
                CreditCardHolder.builder()
                        .creditCardId(savedCreditCard.getId())
                        .firstName(savedCreditCard.getFirstName())
                        .lastName(savedCreditCard.getLastName())
                        .zipCode(savedCreditCard.getZipCode())
                        .build());
        creditCardPanRepository.save(
                CreditCardPan.builder()
                        .creditCardId(savedCreditCard.getId())
                        .creditCardNumber(savedCreditCard.getCreditCardNumber())
                        .build());
        return savedCreditCard;
    }

    @Override
    public CreditCard getCreditCardById(Long id) {
        final CreditCard creditCard = creditCardRepository.findById(id).orElseThrow();
        final CreditCardHolder creditCardHolder = creditCardHolderRepository.findByCreditCardId(id).orElseThrow();
        final CreditCardPan creditCardPan = creditCardPanRepository.findByCreditCardId(id).orElseThrow();
        creditCard.setFirstName(creditCardHolder.getFirstName());
        creditCard.setLastName(creditCardHolder.getLastName());
        creditCard.setZipCode(creditCardHolder.getZipCode());
        creditCard.setCreditCardNumber(creditCardPan.getCreditCardNumber());
        return creditCard;
    }
}
