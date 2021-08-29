package com.sb.spring.loanapplicationservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerLoanOutDTO {

    List<LoanDTO> loans;
    private CustomerDTO customer;

}
