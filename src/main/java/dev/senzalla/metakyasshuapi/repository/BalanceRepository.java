package dev.senzalla.metakyasshuapi.repository;

import dev.senzalla.metakyasshuapi.model.balance.entity.Balance;
import dev.senzalla.metakyasshuapi.model.balance.module.BalanceFilter;
import dev.senzalla.metakyasshuapi.model.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {

    @Query("SELECT b FROM Balance b WHERE b.user = :user AND " +
           "(:#{#balanceFilter.descriptionBalance} IS NULL OR b.descriptionBalance LIKE %:#{#balanceFilter.descriptionBalance}%) AND " +
           "(:#{#balanceFilter.category} IS NULL OR b.category = :#{#balanceFilter.category}) AND " +
           "(:#{#balanceFilter.dateBalanceStart} IS NULL OR b.dateBalance >= :#{#balanceFilter.dateBalanceStart}) AND " +
           "(:#{#balanceFilter.dateBalanceEnd} IS NULL OR b.dateBalance <= :#{#balanceFilter.dateBalanceEnd})")
    Page<Balance> findAllByUserAndFilter(@Param("user") User user,
                                         @Param("balanceFilter") BalanceFilter balanceFilter,
                                         Pageable pageable);
}