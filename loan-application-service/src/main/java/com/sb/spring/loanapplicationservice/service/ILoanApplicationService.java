package com.sb.spring.loanapplicationservice.service;

import com.sb.spring.loanapplicationservice.model.CustomerLoanOutDTO;
import com.sb.spring.loanapplicationservice.model.CustomerSearchDTO;
import com.sb.spring.loanapplicationservice.model.LoanApplicationDTO;

import java.util.List;

public interface ILoanApplicationService {
    List<LoanApplicationDTO> getAllLoans();

    Integer createLoanApplication(LoanApplicationDTO loanApplicationDTO);

    CustomerLoanOutDTO getLoansByCustomerID(Integer customerId);

    List<CustomerSearchDTO> searchCustomers(Integer minAmount, Integer maxAmount, String status);
}
