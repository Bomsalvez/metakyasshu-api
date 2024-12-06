package dev.senzalla.metakyasshuapi.repository;

import dev.senzalla.metakyasshuapi.model.participation.entity.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation, Long> {

    @Query("SELECT p FROM Participation p " +
            "WHERE p.collaborator.userCollaborator.pkUser = :pkUser " +
            "AND p.expense.pkExpense = :pkExpense")
    Optional<Participation> findByUserCollaboratorAndExpense(Long pkUser, Long pkExpense);
}