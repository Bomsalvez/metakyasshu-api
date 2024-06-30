package dev.senzalla.metakyasshuapi.repository;

import dev.senzalla.metakyasshuapi.model.card.entity.Card;
import dev.senzalla.metakyasshuapi.model.card.module.CardFilter;
import dev.senzalla.metakyasshuapi.model.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    @Query("SELECT c FROM Card c " +
            "WHERE c.user = :user " +
            "AND (:#{#cardFilter.nameCard} IS NULL OR c.nameCard like %:#{#cardFilter.nameCard}%) " +
            "AND (:#{#cardFilter.numberCard} IS NULL OR c.numberCard like %:#{#cardFilter.numberCard}%) " +
            "AND (:#{#cardFilter.typeCard} IS NULL OR c.typeCard = :#{#cardFilter.typeCard})")
    Page<Card> findAllByUser(User user, CardFilter cardFilter, Pageable pageable);
}