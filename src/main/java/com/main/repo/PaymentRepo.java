package com.main.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.entity.Payment;

public interface PaymentRepo  extends JpaRepository<Payment, Integer>{

}
