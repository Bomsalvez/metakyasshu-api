package dev.senzalla.metakyasshuapi.model.card.mapper;

import dev.senzalla.metakyasshuapi.model.InterfaceMapper;
import dev.senzalla.metakyasshuapi.model.card.entity.Card;
import dev.senzalla.metakyasshuapi.model.card.module.CardDto;
import dev.senzalla.metakyasshuapi.model.card.module.CardForm;
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
        return cardDto;
    }

    @Override
    public Card toEntity(CardForm cardForm) {
        Card card = new Card();
        card.setNameCard(cardForm.getNameCard().replaceAll("\\s+", " "));
        card.setNumberCard(cardForm.getNumberCard());
        card.setValidateCard(cardForm.getValidateCard());
        card.setTypeCard(cardForm.getTypeCard());
        return card;
    }

    @Override
    public CardDto toSummarized(Card card) {
        return null;
    }
}
