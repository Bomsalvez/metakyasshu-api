package dev.senzalla.metakyasshuapi.repository;

import dev.senzalla.metakyasshuapi.model.expense.entity.Expense;
import dev.senzalla.metakyasshuapi.model.expense.module.ExpenseFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    @Query("SELECT e FROM Expense e WHERE " +
            "(:#{#expenseFilter.nameExpense} IS NULL OR e.nameExpense LIKE %:#{#expenseFilter.nameExpense}%) AND " +
            "(:#{#expenseFilter.term} IS NULL OR e.dueDateExpense BETWEEN :#{#expenseFilter.startDate} AND :#{#expenseFilter.endDate}) AND " +
            "(:#{#expenseFilter.dueDateExpense} IS NULL OR e.dueDateExpense = :#{#expenseFilter.dueDateExpense}) AND " +
            "(:#{#expenseFilter.typeExpense} IS NULL OR e.typeExpense = :#{#expenseFilter.typeExpense}) AND " +
            "(:#{#expenseFilter.accessLevel} IS NULL OR e.accessLevel = :#{#expenseFilter.accessLevel}) AND " +
            "(:#{#expenseFilter.category} IS NULL OR e.category = :#{#expenseFilter.category}) AND " +
            "(:#{#expenseFilter.user} IS NULL OR e.user.pkUser = :#{#expenseFilter.user.pkUser})")
    Page<Expense> findExpense(ExpenseFilter expenseFilter, Pageable pageable);
}