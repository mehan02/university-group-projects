package com.ooad.inventorymanagmentsystem.repositories;

import com.ooad.inventorymanagmentsystem.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
