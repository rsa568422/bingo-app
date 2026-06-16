package com.rsa.bingo.app.infrastructure.mappers.defaults;

import com.rsa.bingo.app.infrastructure.entities.CardEntity;
import com.rsa.bingo.app.infrastructure.mappers.CardMapper;
import com.rsa.bingo.app.infrastructure.mappers.PlayerMapper;
import com.rsa.bingo.app.infrastructure.utils.CardValuesDecoder;
import com.rsa.bingo.app.infrastructure.utils.IterableMapper;
import com.rsa.bingo.domain.models.Card;
import org.json.JSONArray;

public class DefaultCardMapper implements CardMapper {

    private final PlayerMapper playerMapper;

    public DefaultCardMapper(PlayerMapper playerMapper) {
        this.playerMapper = playerMapper;
    }

    @Override
    public Card toCard(CardEntity cardEntity) {
        var values = CardValuesDecoder.decode(cardEntity.getValues());
        return new Card(cardEntity.getId(), values, playerMapper.toPlayer(cardEntity.getPlayer()));
    }

    @Override
    public Iterable<Card> toCards(Iterable<CardEntity> cardEntities) {
        return IterableMapper.mapToList(cardEntities, this::toCard);
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
