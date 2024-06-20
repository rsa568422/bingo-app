package com.rsa.bingo.app.infrastructure.mappers;

import com.rsa.bingo.app.infrastructure.entities.CardEntity;
import com.rsa.bingo.domain.models.Card;
import org.springframework.stereotype.Component;

@Component
public class CardMapperImpl implements CardMapper {

    @Override
    public Card toCard(CardEntity cardEntity) {
        return null;
    }

    @Override
    public Iterable<Card> toCards(Iterable<CardEntity> cardEntities) {
        return null;
    }

    @Override
    public CardEntity toCardEntity(Card card) {
        return null;
    }
}
