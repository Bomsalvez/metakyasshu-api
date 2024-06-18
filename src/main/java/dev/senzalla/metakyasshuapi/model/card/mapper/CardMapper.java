package dev.senzalla.metakyasshuapi.model.card.mapper;

import dev.senzalla.metakyasshuapi.model.InterfaceMapper;
import dev.senzalla.metakyasshuapi.model.card.entity.Card;
import dev.senzalla.metakyasshuapi.model.card.module.CardDto;
import dev.senzalla.metakyasshuapi.model.card.module.CardForm;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class CardMapper implements InterfaceMapper<CardDto, Card, CardForm, CardDto> {
    @Override
    public CardDto toDto(Card card) {
        CardDto cardDto = new CardDto();
        cardDto.setPkCard(card.getPkCard());
        cardDto.setNameCard(card.getNameCard());
        cardDto.setNumberCard(card.getNumberCard());
        cardDto.setValidateCard(card.getValidateCard());
        cardDto.setTypeCard(card.getTypeCard());
        cardDto.setFlagCard(card.getFlagCard());
        return cardDto;
    }

    @Override
    public Card toEntity(CardForm cardForm) {
        Card card = new Card();
        card.setNameCard(cardForm.getNameCard().replaceAll("\\s+", " "));
        card.setNumberCard(cardForm.getNumberCard());
        card.setValidateCard(cardForm.getValidateCard());
        card.setTypeCard(cardForm.getTypeCard());
        card.setFlagCard(cardForm.getFlagCard().replaceAll("\\s+", " "));
        return card;
    }

    @Deprecated(since = "1.0.0", forRemoval = true)
    @Override
    public Page<CardDto> toSummarized(Page<Card> card) {
        throw new UnsupportedOperationException();
    }

    public Card toEntity(CardDto cardDto) {
        Card card = new Card();
        card.setNameCard(cardDto.getNameCard().replaceAll("\\s+", " "));
        card.setNumberCard(cardDto.getNumberCard());
        card.setValidateCard(cardDto.getValidateCard());
        card.setTypeCard(cardDto.getTypeCard());
        card.setFlagCard(cardDto.getFlagCard().replaceAll("\\s+", " "));
        return card;
    }

    public Card toEntityExpense(CardDto cardDto) {
        Card card = toEntity(cardDto);
        card.setPkCard(cardDto.getPkCard());
        return card;
    }
}
