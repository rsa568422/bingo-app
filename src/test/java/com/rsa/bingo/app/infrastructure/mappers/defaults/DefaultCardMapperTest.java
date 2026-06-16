package com.rsa.bingo.app.infrastructure.mappers.defaults;

import com.rsa.bingo.app.TestData;
import com.rsa.bingo.app.infrastructure.entities.CardEntity;
import com.rsa.bingo.domain.models.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DefaultCardMapperTest {

    private DefaultCardMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new DefaultCardMapper(new DefaultPlayerMapper());
    }

    @Test
    void toCard_convertsEntityToDomain() {
        var entity = TestData.cardEntity();
        Card card = mapper.toCard(entity);
        assertThat(card.getId()).isEqualTo(entity.getId());
        assertThat(card.getValues()).isDeepEqualTo(TestData.VALUES);
        assertThat(card.getPlayer().getId()).isEqualTo(entity.getPlayer().getId());
        assertThat(card.getPlayer().getName()).isEqualTo(entity.getPlayer().getName());
    }

    @Test
    void toCards_convertsMultipleEntities() {
        List<CardEntity> entities = List.of(TestData.cardEntity());
        Iterable<Card> cards = mapper.toCards(entities);
        assertThat(cards).hasSize(1);
    }

    @Test
    void toCards_emptyList_returnsEmpty() {
        Iterable<Card> cards = mapper.toCards(Collections.emptyList());
        assertThat(cards).isEmpty();
    }

    @Test
    void toCardEntity_convertsDomainToEntity() {
        var card = TestData.card();
        CardEntity entity = mapper.toCardEntity(card);
        assertThat(entity.getId()).isEqualTo(card.getId());
        assertThat(entity.getValues()).isNotBlank();
        assertThat(entity.getPlayer().getId()).isEqualTo(card.getPlayer().getId());
        assertThat(entity.getPlayer().getName()).isEqualTo(card.getPlayer().getName());
    }

    @Test
    void roundTrip_entityToDomainAndBack() {
        var original = TestData.cardEntity();
        var card = mapper.toCard(original);
        var result = mapper.toCardEntity(card);
        assertThat(result.getId()).isEqualTo(original.getId());
        assertThat(result.getPlayer().getId()).isEqualTo(original.getPlayer().getId());
    }
}
