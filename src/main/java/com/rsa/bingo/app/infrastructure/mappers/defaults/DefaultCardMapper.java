package com.rsa.bingo.app.infrastructure.mappers.defaults;

import com.rsa.bingo.app.infrastructure.entities.CardEntity;
import com.rsa.bingo.app.infrastructure.mappers.CardMapper;
import com.rsa.bingo.app.infrastructure.mappers.PlayerMapper;
import com.rsa.bingo.domain.models.Card;
import org.apache.commons.collections4.IterableUtils;
import org.json.JSONArray;

public class DefaultCardMapper implements CardMapper {

    private final PlayerMapper playerMapper;

    public DefaultCardMapper(PlayerMapper playerMapper) {
        this.playerMapper = playerMapper;
    }

    @Override
    public Card toCard(CardEntity cardEntity) {
        var json = new JSONArray(cardEntity.getValues());
        var values = new Integer[3][9];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 9; j++)
                values[i][j] = json.getJSONArray(i).optIntegerObject(j, null);
        return new Card(cardEntity.getId(), values, playerMapper.toPlayer(cardEntity.getPlayer()));
    }

    @Override
    public Iterable<Card> toCards(Iterable<CardEntity> cardEntities) {
        return IterableUtils.toList(cardEntities).stream().map(this::toCard).toList();
    }

    @Override
    public CardEntity toCardEntity(Card card) {
        var cardEntity = new CardEntity();
        cardEntity.setId(card.getId());
        cardEntity.setValues(new JSONArray(card.getValues()).toString());
        cardEntity.setPlayer(playerMapper.toPlayerEntity(card.getPlayer()));
        return cardEntity;
    }
}
