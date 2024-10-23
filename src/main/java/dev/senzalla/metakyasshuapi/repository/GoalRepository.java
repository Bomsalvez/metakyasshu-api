package dev.senzalla.metakyasshuapi.repository;

import dev.senzalla.metakyasshuapi.model.goal.entity.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {
}