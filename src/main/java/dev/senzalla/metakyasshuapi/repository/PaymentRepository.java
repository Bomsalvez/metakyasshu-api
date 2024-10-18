package dev.senzalla.metakyasshuapi.repository;

import dev.senzalla.metakyasshuapi.model.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}