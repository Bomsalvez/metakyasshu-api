package dev.senzalla.metakyasshuapi.repository;

import dev.senzalla.metakyasshuapi.model.collaborator.entity.Collaborator;
import dev.senzalla.metakyasshuapi.model.collaborator.module.CollaboratorFilter;
import dev.senzalla.metakyasshuapi.model.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CollaboratorRepository extends JpaRepository<Collaborator, Long> {
    Optional<Collaborator> findByUserCollaboratorAndUserHost(User userCollaborator, User userHost);

    @Query("SELECT c FROM Collaborator c " +
            "WHERE (c.acceptDateCollaborator IS NOT NULL) " +
            "AND (:#{#filter.userCollaborator} IS NULL OR c.userCollaborator.nameUser LIKE %:#{#filter.userCollaborator}%) " +
            "AND (:#{#filter.userHost} IS NULL OR c.userHost.nameUser LIKE %:#{#filter.userHost}%) " +
            "AND (:#{#filter.hasCollaborator} = false AND c.userHost = :user) " +
            "OR (:#{#filter.hasCollaborator} = true AND c.userCollaborator = :user) ")
    Page<Collaborator> findAllByUser(User user, CollaboratorFilter filter, Pageable pageable);
}