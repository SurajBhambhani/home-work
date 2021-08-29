package com.sb.spring.customerservice.customer;

import com.sb.spring.customerservice.exception.CustomerNotFoundException;
import com.sb.spring.customerservice.model.ErrorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    ICustomerService customerService;


    /**
     * Method for finding customer,
     *
     * TODO message need to be picked from properties file.
     *      Exception handling can be done by @ExceptionHandler
     * @param id
     * @return
     */
    @GetMapping(value = "/customers/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable("id") Integer id) {
        try {
            return ResponseEntity.ok().body(customerService.getCustomer(id));
        } catch (CustomerNotFoundException customerNotFoundException) {
            return new ResponseEntity<>(new ErrorDTO("CUSTOMER_NOT_FOUND", "Customer doesn't exist in system."), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/customers/all")
    public ResponseEntity<?> getAllCustomers() {
        return ResponseEntity.ok().body(customerService.getAllCustomers());
    }

    @PostMapping("/customers/byid")
    public ResponseEntity<?> getCustomersById(@RequestBody List<Integer> ids) {
        return ResponseEntity.ok().body(customerService.getCustomersByCustomerId(ids));
    }


    @PostMapping("/customers")
    public ResponseEntity<?> register(@RequestBody @Valid CustomerDTO customerDTO) {

        String customerId = customerService.createCustomer(customerDTO);
        return ResponseEntity.ok().body(new CustomerOutDTO(customerId));
    }
}
