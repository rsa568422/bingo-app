package com.rsa.bingo.app.infrastructure;

import com.rsa.bingo.domain.models.Color;
import com.rsa.bingo.domain.models.Customization;

public final class Constants {

    private Constants() { }

    public static final String PLAYERS = "players";
    public static final String PLAYER = "player";
    public static final String CARDS = "cards";
    public static final String CARD = "card";
    public static final String COLORS = "colors";
    public static final String COLOR = "color";
    public static final String CUSTOMIZATION = "customization";
    public static final String CUSTOMIZATIONS = "customizations";

    public static Customization defaultCustomization(Integer cardId) {
        return new Customization(cardId, Color.BLACK, Color.GREY_50_PERCENT);
    }
}
