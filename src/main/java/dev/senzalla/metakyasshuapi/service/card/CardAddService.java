package dev.senzalla.metakyasshuapi.service.card;

import dev.senzalla.metakyasshuapi.model.card.entity.Card;
import dev.senzalla.metakyasshuapi.model.card.entity.CardRepository;
import dev.senzalla.metakyasshuapi.model.card.mapper.CardMapper;
import dev.senzalla.metakyasshuapi.model.card.module.CardForm;
import dev.senzalla.metakyasshuapi.model.user.entity.User;
import dev.senzalla.metakyasshuapi.service.tools.MessageDecode;
import dev.senzalla.metakyasshuapi.service.user.UserService;
import dev.senzalla.metakyasshuapi.settings.exception.DuplicateException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class CardAddService {
    private final MessageDecode messageDecode;
    private final CardRepository repository;
    private final UserService userService;
    private final CardMapper mapper;


    public void save(CardForm cardForm, String token) {
       try {
           User user = userService.findByToken(token);
           Card card = mapper.toEntity(cardForm);
           card.setUser(user);
           repository.save(card);
       } catch (DataIntegrityViolationException e) {
           String error = messageDecode.extractMessage(e.getMessage());
           log.error("Error adding card: {}", error);
           String message = MessageDecode.decodeUnique(e.getMessage());
           throw new DuplicateException("error.duplicate", message);
       }
    }
}
