package com.rsa.bingo.app.infrastructure.dtos;

import com.rsa.bingo.domain.models.Card;
import com.rsa.bingo.domain.models.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONArray;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardDTO {

    private Integer id;

    private Integer[][] values = new Integer[3][9];

    private Integer playerId;

    private String playerName;

    public CardDTO(Integer id, String json, Integer playerId, String playerName) {
        this(id, decode(json), playerId, playerName);
    }

    public String getValuesJson () {
        return new JSONArray(values).toString();
    }

    public Card toCard() {
        return new Card(id, values, new Player(playerId, playerName));
    }

    public static CardDTO valueOf(Card card) {
        return new CardDTO(card.getId(), card.getValues(), card.getPlayer().getId(), card.getPlayer().getName());
    }

    private static Integer[][] decode(String json) {
        var rows = new JSONArray(json);
        var values = new Integer[3][9];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 9; j++)
                values[i][j] = rows.getJSONArray(i).optIntegerObject(j, null);
        return values;
    }
}
