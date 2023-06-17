package com.mcfly.creditcard.domain.pan;

import com.mcfly.creditcard.converters.CvvConverter;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreditCardPan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = CvvConverter.class)
    private String creditCardNumber;

    private Long creditCardId;
}
