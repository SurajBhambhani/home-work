package com.sb.spring.loanapplicationservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanDTO {

    private Integer id;

    private Integer amount;

    private Integer duration;

    private String status;
}
