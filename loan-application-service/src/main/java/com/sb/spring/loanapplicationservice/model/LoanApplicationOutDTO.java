package com.sb.spring.loanapplicationservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created separate object so that in future we can send more values if required.
 */
@Data
@AllArgsConstructor
public class LoanApplicationOutDTO {
    private Integer id;
}

