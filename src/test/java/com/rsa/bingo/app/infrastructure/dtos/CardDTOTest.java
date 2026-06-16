package com.rsa.bingo.app.infrastructure.dtos;

import com.rsa.bingo.app.TestData;
import com.rsa.bingo.domain.models.Card;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardDTOTest {

    @Test
    void toCard_convertsCorrectly() {
        var dto = TestData.cardDTO();
        Card card = dto.toCard();
        assertThat(card.getId()).isEqualTo(1);
        assertThat(card.getValues()).isDeepEqualTo(TestData.VALUES);
        assertThat(card.getPlayer().getId()).isEqualTo(1);
        assertThat(card.getPlayer().getName()).isEqualTo("TestPlayer");
    }

    @Test
    void valueOf_convertsFromCard() {
        var card = TestData.card();
        CardDTO dto = CardDTO.valueOf(card);
        assertThat(dto.getId()).isEqualTo(card.getId());
        assertThat(dto.getValues()).isDeepEqualTo(card.getValues());
        assertThat(dto.getPlayerId()).isEqualTo(card.getPlayer().getId());
        assertThat(dto.getPlayerName()).isEqualTo(card.getPlayer().getName());
    }

    @Test
    void getValuesJson_returnsValidJson() throws Exception {
        var dto = TestData.cardDTO();
        String json = dto.getValuesJson();
        var parsed = new JSONArray(json);
        assertThat(parsed.length()).isEqualTo(3);
        assertThat(parsed.getJSONArray(0).length()).isEqualTo(9);
    }

    @Test
    void jsonConstructor_decodesCorrectly() {
        String json = TestData.valuesJson();
        var dto = new CardDTO(1, json, 1, "TestPlayer");
        assertThat(dto.getValues()).isDeepEqualTo(TestData.VALUES);
        assertThat(dto.getId()).isEqualTo(1);
    }

    @Test
    void roundTrip_preservesData() {
        var original = TestData.cardDTO();
        var card = original.toCard();
        var result = CardDTO.valueOf(card);
        assertThat(result.getId()).isEqualTo(original.getId());
        assertThat(result.getValues()).isDeepEqualTo(original.getValues());
        assertThat(result.getPlayerId()).isEqualTo(original.getPlayerId());
    }

    @Test
    void noArgConstructor_createsInstanceWithDefaultValues() {
        var dto = new CardDTO();
        assertThat(dto.getId()).isNull();
        assertThat(dto.getValues()).isNotNull();
        assertThat(dto.getPlayerId()).isNull();
    }

    @Test
    void setters_updateFields() {
        var dto = new CardDTO();
        dto.setId(10);
        dto.setPlayerId(5);
        dto.setPlayerName("NewPlayer");
        assertThat(dto.getId()).isEqualTo(10);
        assertThat(dto.getPlayerId()).isEqualTo(5);
        assertThat(dto.getPlayerName()).isEqualTo("NewPlayer");
    }
}
