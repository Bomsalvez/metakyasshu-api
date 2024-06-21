package dev.senzalla.metakyasshuapi.repository;

import dev.senzalla.metakyasshuapi.model.invitation.entity.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Long> {
}