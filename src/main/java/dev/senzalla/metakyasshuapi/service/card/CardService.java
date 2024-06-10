package dev.senzalla.metakyasshuapi.service.card;

import dev.senzalla.metakyasshuapi.model.card.module.CardDto;
import dev.senzalla.metakyasshuapi.model.card.module.CardFilter;
import dev.senzalla.metakyasshuapi.model.card.module.CardForm;
import dev.senzalla.metakyasshuapi.service.InterfaceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CardService implements InterfaceService<CardDto, CardFilter, CardForm, Void> {
    private final CardAddService addService;

    @Override
    public void save(CardForm cardForm, String token) {
        addService.save(cardForm, token);
    }

    @Override
    public void update(Long pk, CardForm cardForm) {

    }

    @Override
    public void delete(Long pk) {

    }

    @Override
    public CardDto find(Long pk) {
        return null;
    }

    @Override
    public List<Void> findAll() {
        return List.of();
    }

    @Override
    @Deprecated
    public Page<Void> findAll(CardFilter cardFilter) {
        throw new UnsupportedOperationException();
    }
}
