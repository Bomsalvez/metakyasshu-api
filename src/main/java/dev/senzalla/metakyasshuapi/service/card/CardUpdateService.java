package dev.senzalla.metakyasshuapi.service.card;

import dev.senzalla.metakyasshuapi.model.card.entity.Card;
import dev.senzalla.metakyasshuapi.model.card.mapper.CardMapper;
import dev.senzalla.metakyasshuapi.model.card.module.CardDto;
import dev.senzalla.metakyasshuapi.model.card.module.CardForm;
import dev.senzalla.metakyasshuapi.repository.CardRepository;
import dev.senzalla.metakyasshuapi.service.tools.MessageDecode;
import dev.senzalla.metakyasshuapi.settings.exception.DuplicateException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class CardUpdateService {
    private final MessageDecode messageDecode;
    private final CardRepository repository;
    private final CardFindService findService;
    private final CardMapper mapper;

    public CardDto update(Long pk, CardForm cardForm) {
        try {
            Card oldCard = findService.findCardEntity(pk);
            Card card = mapper.toEntity(cardForm);
            CardToolService.checkFillingDateFieldCreditCase(card);
            card.setUser(oldCard.getUser());
            card.setPkCard(pk);
            repository.save(card);
            return mapper.toDto(card);
        } catch (DataIntegrityViolationException e) {
            String error = messageDecode.extractMessage(e.getMessage());
            log.error("Error updating card: {}", error);
            String message = MessageDecode.decodeUnique(e.getMessage());
            throw new DuplicateException("error.duplicate", message);
        }
    }
}
