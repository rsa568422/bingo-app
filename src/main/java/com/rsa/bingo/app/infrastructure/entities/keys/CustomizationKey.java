package com.rsa.bingo.app.infrastructure.entities.keys;

import com.rsa.bingo.app.infrastructure.entities.CardEntity;
import com.rsa.bingo.app.infrastructure.entities.ColorsEntity;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Embeddable
public class CustomizationKey {

    @ManyToOne
    @JoinColumn(name = "card_id", referencedColumnName = "id")
    private CardEntity card;

    @ManyToOne
    @JoinColumn(name = "colors_id", referencedColumnName = "id")
    private ColorsEntity colors;
}
