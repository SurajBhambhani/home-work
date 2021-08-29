package com.sb.spring.customerservice.customer;

import com.sb.spring.customerservice.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {

    Function<CustomerEntity, CustomerDTO> customerEntityCustomerDTOFunction = customerEntity -> new CustomerDTO(customerEntity.getId(),
            customerEntity.getUserId(),
            customerEntity.getFirstName(),
            customerEntity.getLastName(),
            customerEntity.getEmail(),
            customerEntity.getPhone());

    @Autowired
    ICustomerRepository iCustomerRepository;

    /**
     * Method for creating new Customer.
     *
     * @param customerDTO
     * @return customerId
     */
    @Override
    public String createCustomer(CustomerDTO customerDTO) {
        log.info("createCustomer: Entering customer Information: {}", customerDTO.getUserId());

        String customerIdString;
        // If customer already exist is system so returning result
        Optional<CustomerEntity> presentCustomerEntity = iCustomerRepository.findByUserId(customerDTO.getUserId());
        if (presentCustomerEntity.isPresent()) {
            customerIdString = Integer.toString(presentCustomerEntity.get().getId());
            log.info("createCustomer: Customer is returning user: {}", customerIdString);
            return customerIdString;
        }
        CustomerEntity customerEntity = new CustomerEntity();
        BeanUtils.copyProperties(customerDTO, customerEntity);

        customerIdString = Integer.toString(iCustomerRepository.save(customerEntity).getId());
        log.info("saved new customer: Customer id is: {}", customerIdString);

        return customerIdString;
    }

    /**
     * Method to return all customers
     *
     * @return List<CustomerDTO>
     */
    @Override
    public List<CustomerDTO> getAllCustomers() {

        return iCustomerRepository.findAll().parallelStream().map(
                customerEntityCustomerDTOFunction).collect(Collectors.toList());
    }

    /**
     * Method to customer by Id
     *
     * @return CustomerDTO
     */
    @Override
    public CustomerDTO getCustomer(Integer id) {

        log.info("getCustomer: finding customer by id: {}", id);

        CustomerDTO customerDTO = null;
        if (iCustomerRepository.findById(id).isPresent()) {
            CustomerEntity customerEntity = iCustomerRepository.findById(id).get();
            customerDTO = new CustomerDTO();
            BeanUtils.copyProperties(customerEntity, customerDTO);
        } else {
            log.error("getCustomer: customer not found {}", id);
            throw new CustomerNotFoundException();
        }
        log.info("getCustomer: Returning customer by id: {}", id);
        return customerDTO;
    }

    @Override
    public List<CustomerDTO> getCustomersByCustomerId(List<Integer> ids) {
        return iCustomerRepository.findByIdsIn(ids).parallelStream().map(
                customerEntityCustomerDTOFunction).collect(Collectors.toList());
    }

}
