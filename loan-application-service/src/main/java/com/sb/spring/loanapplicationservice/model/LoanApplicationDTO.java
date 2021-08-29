package com.sb.spring.loanapplicationservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanApplicationDTO {

    private Integer id;

    @NotBlank
    private String customerId;

    @Range(min = 1)
    private Integer amount;

    @NotBlank
    private String duration;

    private String status;
}
