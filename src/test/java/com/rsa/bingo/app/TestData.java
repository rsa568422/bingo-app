package com.rsa.bingo.app;

import com.rsa.bingo.app.infrastructure.dtos.CardDTO;
import com.rsa.bingo.app.infrastructure.dtos.CustomizationDTO;
import com.rsa.bingo.app.infrastructure.dtos.PlayerDTO;
import com.rsa.bingo.app.infrastructure.entities.CardEntity;
import com.rsa.bingo.app.infrastructure.entities.CustomizationEntity;
import com.rsa.bingo.app.infrastructure.entities.PlayerEntity;
import com.rsa.bingo.app.infrastructure.entities.keys.CustomizationKey;
import com.rsa.bingo.domain.models.Card;
import com.rsa.bingo.domain.models.Color;
import com.rsa.bingo.domain.models.Customization;
import com.rsa.bingo.domain.models.Player;
import org.json.JSONArray;

public final class TestData {

    private TestData() { }

    public static Integer[][] VALUES = {
            {null, 18, 24, null, null, 54, 67, null, 80},
            {null, null, 26, null, 41, 55, null, 70, 87},
            {4, null, null, 39, 42, null, 69, 72, null}
    };

    public static String valuesJson() {
        try {
            return new JSONArray(VALUES).toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Player player() {
        return new Player(1, "TestPlayer");
    }

    public static Card card() {
        return new Card(1, VALUES, player());
    }

    public static Customization customization() {
        return new Customization(1, Color.BLACK, Color.GREY_50_PERCENT);
    }

    public static PlayerEntity playerEntity() {
        var entity = new PlayerEntity();
        entity.setId(1);
        entity.setName("TestPlayer");
        return entity;
    }

    public static CardEntity cardEntity() {
        var entity = new CardEntity();
        entity.setId(1);
        entity.setValues(valuesJson());
        entity.setPlayer(playerEntity());
        return entity;
    }

    public static CustomizationEntity customizationEntity() {
        var card = new CardEntity();
        card.setId(1);
        var key = new CustomizationKey();
        key.setCard(card);
        key.setPrimaryColor(Color.BLACK.getName());
        key.setSecondaryColor(Color.GREY_50_PERCENT.getName());
        var entity = new CustomizationEntity();
        entity.setKey(key);
        return entity;
    }

    public static PlayerDTO playerDTO() {
        return new PlayerDTO(1, "TestPlayer");
    }

    public static CardDTO cardDTO() {
        return new CardDTO(1, VALUES, 1, "TestPlayer");
    }

    public static CustomizationDTO customizationDTO() {
        return new CustomizationDTO(1, Color.BLACK, Color.GREY_50_PERCENT);
    }
}
