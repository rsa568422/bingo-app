package com.rsa.bingo.app.infrastructure.dtos;

import com.rsa.bingo.domain.models.Color;
import com.rsa.bingo.domain.models.Customization;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomizationDTO {

    private Integer cardId;

    private String primaryName;

    private int[] primaryRGB;

    private String secondaryName;

    private int[] secondaryRGB;

    public Customization toCustomization() {
        return new Customization(cardId, Color.valueOf(primaryName), Color.valueOf(secondaryName));
    }

    public static CustomizationDTO valueOf(Customization customization) {
        return new CustomizationDTO(
                customization.getCardId(),
                customization.getPrimary().getName(),
                customization.getPrimary().getRgb(),
                customization.getSecondary().getName(),
                customization.getSecondary().getRgb()
        );
    }
}
