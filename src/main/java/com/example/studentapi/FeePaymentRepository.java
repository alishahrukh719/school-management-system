package com.example.studentapi;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FeePaymentRepository extends JpaRepository<FeePayment, String> {
}
