package dev.senzalla.metakyasshuapi.service.card;

import dev.senzalla.metakyasshuapi.model.card.entity.Card;
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
    // TODO: realizar teste apos adicionar despesas

    public void delete(Long pk) {
        Card card = findService.findCardEntity(pk);
        boolean hasUnpaidExpenses = card.getExpenses().stream().anyMatch(expense -> expense.getPayment() == null);
        if (hasUnpaidExpenses) {
            String entityCard = messageDecode.info("entity.card");
            String entityExpense = messageDecode.info("entity.expense");
            throw new ExcludeException(messageDecode.info("error.exclude"), entityCard, entityExpense);
        }
    }
}