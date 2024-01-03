package com.bootcat.BootcatBank.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcat.BootcatBank.model.TransactionHistory;

public interface TransactionRepo extends JpaRepository<TransactionHistory, Integer> {

}