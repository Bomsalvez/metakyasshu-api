package dev.senzalla.metakyasshuapi.service.card;

import dev.senzalla.metakyasshuapi.model.card.entity.Card;
import dev.senzalla.metakyasshuapi.model.card.mapper.CardMapper;
import dev.senzalla.metakyasshuapi.model.card.module.CardDto;
import dev.senzalla.metakyasshuapi.model.card.module.CardFilter;
import dev.senzalla.metakyasshuapi.model.user.entity.User;
import dev.senzalla.metakyasshuapi.repository.CardRepository;
import dev.senzalla.metakyasshuapi.service.user.UserService;
import dev.senzalla.metakyasshuapi.settings.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class CardFindService {
    private final CardRepository repository;
    private final UserService userService;
    private final CardMapper mapper;

    public Card findCardEntity(Long pk) {
        Optional<Card> card = repository.findById(pk);
        if (card.isEmpty()) {
            throw new NotFoundException("error.not-found", "card");
        }
        return card.get();
    }

    public CardDto find(Long pk) {
        Card card = findCardEntity(pk);
        return mapper.toDto(card);
    }

    public Page<CardDto> findAll(CardFilter cardFilter, String token, Pageable pageable) {
        User user = userService.findByToken(token);
        Page<Card> cards = repository.findAllByUser(user, cardFilter, pageable);
        return mapper.toSummarized(cards);
    }
}
