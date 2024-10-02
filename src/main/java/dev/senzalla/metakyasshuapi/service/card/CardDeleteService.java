package dev.senzalla.metakyasshuapi.service.card;

import dev.senzalla.metakyasshuapi.model.card.entity.Card;
import dev.senzalla.metakyasshuapi.repository.CardRepository;
import dev.senzalla.metakyasshuapi.service.tools.MessageDecode;
import dev.senzalla.metakyasshuapi.settings.exception.ExcludeException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class CardDeleteService {
    private final CardFindService findService;
    private final MessageDecode messageDecode;
    private final CardRepository repository;
    // TODO: realizar teste apos adicionar despesas

    public void delete(Long pk) {
        Card card = findService.findCardEntity(pk);
        boolean hasUnpaidExpenses = card.getExpenses().stream().anyMatch(expense -> expense.getPayment() == null);
        if (hasUnpaidExpenses) {
            String entityCard = messageDecode.getMessage("entity.card");
            String entityExpense = messageDecode.getMessage("entity.expense");
            throw new ExcludeException(messageDecode.getMessage("error.exclude"), entityCard, entityExpense);
        }
        repository.delete(card);
    }
}
