package dev.senzalla.metakyasshuapi.repository;

import dev.senzalla.metakyasshuapi.model.expense.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}