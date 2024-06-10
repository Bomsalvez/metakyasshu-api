package dev.senzalla.metakyasshuapi.service.card;

import dev.senzalla.metakyasshuapi.model.card.entity.Card;
import dev.senzalla.metakyasshuapi.repository.CardRepository;
import dev.senzalla.metakyasshuapi.model.card.mapper.CardMapper;
import dev.senzalla.metakyasshuapi.model.card.module.CardDto;
import dev.senzalla.metakyasshuapi.model.card.module.CardFilter;
import dev.senzalla.metakyasshuapi.model.user.entity.User;
import dev.senzalla.metakyasshuapi.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class CardFindService {
    private final CardRepository repository;
    private final UserService userService;
    private final CardMapper mapper;

    public List<CardDto> findAll(CardFilter cardFilter, String token) {
        User user = userService.findByToken(token);
        List<Card> cards = repository.findAllByUser(user, cardFilter);
        return cards.stream().map(mapper::toDto).toList();
    }
}
