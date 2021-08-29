package com.sb.spring.customerservice.customer;

import java.util.List;

public interface ICustomerService {

    String createCustomer(CustomerDTO customerDTO);

    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomer(Integer id);

    List<CustomerDTO> getCustomersByCustomerId(List<Integer> ids);
}
