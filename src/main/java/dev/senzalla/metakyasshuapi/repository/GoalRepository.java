package dev.senzalla.metakyasshuapi.repository;

import dev.senzalla.metakyasshuapi.model.goal.entity.Goal;
import dev.senzalla.metakyasshuapi.model.goal.module.GoalFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {

    @Query("SELECT g FROM Goal g WHERE " +
            "(:#{#goalFilter.nameGoal} IS NULL OR g.nameGoal LIKE %:#{#goalFilter.nameGoal}%) AND " +
            "(:#{#goalFilter.term} IS NULL OR g.expirationDateGoal BETWEEN :#{#goalFilter.startDate} AND :#{#goalFilter.endDate}) AND " +
            "(:#{#goalFilter.dateExecutionGoal} IS NULL OR g.dateExecutionGoal = :#{#goalFilter.dateExecutionGoal}) AND " +
            "(:#{#goalFilter.accessLevel} IS NULL OR g.accessLevel = :#{#goalFilter.accessLevel}) AND " +
            "(:#{#goalFilter.category} IS NULL OR g.category = :#{#goalFilter.category}) AND " +
            "(:#{#goalFilter.user} IS NULL OR g.user.pkUser = :#{#goalFilter.user.pkUser})")
    Page<Goal> findGoal(GoalFilter goalFilter, Pageable pageable);
}