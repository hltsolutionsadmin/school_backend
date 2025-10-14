package com.hlt.usermanagement.repository;
import com.hlt.usermanagement.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}

