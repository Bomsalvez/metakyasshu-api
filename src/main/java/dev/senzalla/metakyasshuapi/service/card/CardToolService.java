package dev.senzalla.metakyasshuapi.service.card;

import dev.senzalla.metakyasshuapi.model.card.entity.Card;
import dev.senzalla.metakyasshuapi.model.types.TypeCard;
import dev.senzalla.metakyasshuapi.settings.exception.NotFoundException;

class CardToolService {
    private CardToolService() {
    }

    public static void checkFillingDateFieldCreditCase(Card card) {
        boolean isCredit = card.getTypeCard().equals(TypeCard.CREDIT) || card.getTypeCard().equals(TypeCard.DEBIT_CREDIT);
        if (isCredit && card.getValidateCard() == null) {
            throw new NotFoundException("error.not-found", "cardValidate");
        }
    }
}
