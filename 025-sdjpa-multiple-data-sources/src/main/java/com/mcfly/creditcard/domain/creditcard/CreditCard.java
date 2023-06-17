package com.mcfly.creditcard.domain.creditcard;

import com.mcfly.creditcard.converters.CvvConverter;
import jakarta.persistence.*;
import lombok.*;

/**
 * Created by jt on 6/27/22.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = CvvConverter.class)
    private String cvv;

    private String expirationDate;

    @Transient
    private String creditCardNumber;

    @Transient
    private String firstName;

    @Transient
    private String lastName;

    @Transient
    private String zipCode;
}
