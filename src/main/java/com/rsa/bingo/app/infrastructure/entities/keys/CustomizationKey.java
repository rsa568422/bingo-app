package com.rsa.bingo.app.infrastructure.entities.keys;

import com.rsa.bingo.app.infrastructure.entities.CardEntity;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Embeddable
public class CustomizationKey {

    @ManyToOne
    private CardEntity card;

    private String primaryColor;

    private String secondaryColor;
}
