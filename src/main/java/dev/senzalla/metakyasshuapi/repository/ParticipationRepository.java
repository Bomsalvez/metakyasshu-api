package dev.senzalla.metakyasshuapi.repository;

import dev.senzalla.metakyasshuapi.model.participation.entity.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation, Long> {
}