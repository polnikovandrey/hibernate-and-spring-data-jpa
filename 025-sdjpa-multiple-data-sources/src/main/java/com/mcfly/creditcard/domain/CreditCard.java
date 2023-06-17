package com.mcfly.creditcard.domain;

import jakarta.persistence.*;

/**
 * Created by jt on 6/27/22.
 */
@Entity
@EntityListeners(CreditCardJpaCallback.class)
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String creditCardNumber;

    @Convert(converter = CvvConverter.class)
    private String cvv;

    private String expirationDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    @PrePersist
    public void prePersistCallback() {
        System.out.println("$$$ JPA PrePersist Callback was called");
    }
}
