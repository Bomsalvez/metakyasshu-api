package dev.senzalla.metakyasshuapi.repository;

import dev.senzalla.metakyasshuapi.model.invitation.entity.Invitation;
import dev.senzalla.metakyasshuapi.model.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Long> {
    @Query("SELECT i FROM Invitation i " +
            "WHERE :sent = true AND i.collaborator.userHost = :user " +
            "OR :sent = false AND i.collaborator.userCollaborator = :user " +
            "AND i.acceptanceDateInvitation IS NULL")
    Page<Invitation> findInvite(User user, boolean sent, Pageable pageable);

    @Query("SELECT i FROM Invitation i " +
            "WHERE :pkInvitation = i.pkInvitation " +
            "AND i.collaborator.userCollaborator = :user " +
            "AND i.acceptanceDateInvitation IS NULL")
    Optional<Invitation> findByPkInvitationAndCollaborator(Long pkInvitation, User user);
}