package com.rsa.bingo.app.infrastructure;

import com.rsa.bingo.domain.models.Colors;

public final class Constants {

    private Constants() { }

    public static final String PLAYERS = "players";
    public static final String PLAYER = "player";
    public static final String CARDS = "cards";
    public static final String CARD = "card";
    public static final String COLORS = "colors";
    public static final String COLOR = "color";
    public static final Colors DEFAULT_COLOR = new Colors(0, new int[] {0, 0, 0}, new int[] {128, 128, 128});
}
