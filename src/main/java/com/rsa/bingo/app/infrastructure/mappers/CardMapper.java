package com.rsa.bingo.app.infrastructure.mappers;

import com.rsa.bingo.app.infrastructure.entities.CardEntity;
import com.rsa.bingo.domain.models.Card;

public interface CardMapper {

    Card toCard(CardEntity cardEntity);

    Iterable<Card> toCards(Iterable<CardEntity> cardEntities);

    CardEntity toCardEntity(Card card);
}
