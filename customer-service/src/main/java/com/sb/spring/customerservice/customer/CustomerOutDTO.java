package com.sb.spring.customerservice.customer;

import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * Created separate object so that in future we can send more values if required.
 */
@Data
@AllArgsConstructor
public class CustomerOutDTO {
    private String id;
}
