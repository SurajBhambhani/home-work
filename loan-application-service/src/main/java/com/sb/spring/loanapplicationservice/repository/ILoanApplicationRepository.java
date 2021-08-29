package com.sb.spring.loanapplicationservice.repository;

import com.sb.spring.loanapplicationservice.entity.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ILoanApplicationRepository extends JpaRepository<LoanEntity, Integer> {

    List<LoanEntity> findByCustomerId(String customerId);

    List<LoanEntity> findByStatusAndAmountBetween(String status, Integer minAmount, Integer maxAmount);

}
