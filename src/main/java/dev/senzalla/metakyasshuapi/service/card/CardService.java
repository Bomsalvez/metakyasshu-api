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
public class CardService implements InterfaceService<CardDto, CardFilter, CardForm, CardDto> {
    private final CardUpdateService updateService;
    private final CardFindService findService;
    private final CardAddService addService;

    @Override
    public void save(CardForm cardForm, String token) {
        addService.save(cardForm, token);
    }

    @Override
    public CardDto update(Long pk, CardForm cardForm) {
        return updateService.update(pk, cardForm);
    }

    @Override
    public void delete(Long pk) {

    }

    @Override
    public CardDto find(Long pk) {
        return findService.find(pk);
    }

    @Override
    public List<CardDto> findAll(CardFilter cardFilter, String token) {
        return findService.findAll(cardFilter, token);
    }

    @Override
    @Deprecated(since = "1.0", forRemoval = true)
    public Page<CardDto> findAll(CardFilter cardFilter) {
        throw new UnsupportedOperationException();
    }
}
