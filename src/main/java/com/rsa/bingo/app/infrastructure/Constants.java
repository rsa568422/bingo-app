package com.rsa.bingo.app.infrastructure;

import com.rsa.bingo.app.infrastructure.dtos.CustomizationDTO;
import com.rsa.bingo.domain.models.Color;
import com.rsa.bingo.domain.models.Customization;

public final class Constants {

    private Constants() { }

    public static final String PLAYERS = "players";
    public static final String PLAYER = "player";
    public static final String CARDS = "cards";
    public static final String CARD = "card";
    public static final String COLORS = "colors";
    public static final String CUSTOMIZATION = "customization";
    public static final String CUSTOMIZATIONS = "customizations";

    public static CustomizationDTO defaultCustomization(Integer cardId) {
        return new CustomizationDTO(
                cardId,
                Color.BLACK.getName(),
                Color.BLACK.getRgb(),
                Color.GREY_50_PERCENT.getName(),
                Color.GREY_50_PERCENT.getRgb()
        );
    }
}
