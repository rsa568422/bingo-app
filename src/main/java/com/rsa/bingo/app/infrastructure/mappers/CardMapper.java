package com.rsa.bingo.app.infrastructure.mappers;

import com.rsa.bingo.app.infrastructure.entities.CardEntity;
import com.rsa.bingo.domain.models.Card;
import org.apache.commons.lang3.stream.Streams;
import org.json.JSONArray;
import org.springframework.stereotype.Component;

@Component
public class CardMapper {

    public Card toCard(CardEntity cardEntity) {
        var values = new Integer[3][9];
        var matrix = new JSONArray(cardEntity.getValues());
        var row0 = matrix.getJSONArray(0);
        var row1 = matrix.getJSONArray(1);
        var row2 = matrix.getJSONArray(2);
        for (int i = 0; i < row0.length(); i++) {
            if (!row0.isNull(i)) values[0][i] = row0.optIntegerObject(i);
        }
        for (int i = 0; i < row1.length(); i++) {
            if (!row1.isNull(i)) values[1][i] = row1.optIntegerObject(i);
        }
        for (int i = 0; i < row2.length(); i++) {
            if (!row2.isNull(i)) values[2][i] = row2.optIntegerObject(i);
        }
        return new Card(cardEntity.getId(), values);
    }

    public Iterable<Card> toCards(Iterable<CardEntity> cardEntities) {
        return Streams.of(cardEntities).map(this::toCard).toList();
    }

    public CardEntity toCardEntity(Card card) {
        var cardEntity = new CardEntity();
        cardEntity.setId(card.getId());
        cardEntity.setValues(new JSONArray(card.getValues()).toString());
        return cardEntity;
    }
}
