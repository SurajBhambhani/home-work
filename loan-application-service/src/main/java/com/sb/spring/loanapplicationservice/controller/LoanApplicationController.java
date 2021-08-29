package com.sb.spring.loanapplicationservice.controller;

import com.sb.spring.loanapplicationservice.model.LoanApplicationDTO;
import com.sb.spring.loanapplicationservice.service.ILoanApplicationService;
import com.sb.spring.loanapplicationservice.model.LoanApplicationOutDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class LoanApplicationController {

    @Autowired
    ILoanApplicationService loanApplicationService;

    @GetMapping("/loanapplications/all")
    public ResponseEntity<?> getAllLoans() {
        return ResponseEntity.ok().body(loanApplicationService.getAllLoans());
    }

    @GetMapping("/loanapplications")
    public ResponseEntity<?> getAllLoansByCustomerId(@RequestParam Integer customerId) {
        return ResponseEntity.ok().body(loanApplicationService.getLoansByCustomerID(customerId));
    }

    @PostMapping("/loanapplications")
    public ResponseEntity<?> register(@RequestBody @Valid LoanApplicationDTO loanApplicationDTO) {

        Integer loanApplicationId = loanApplicationService.createLoanApplication(loanApplicationDTO);
        return ResponseEntity.ok().body(new LoanApplicationOutDTO(loanApplicationId));
    }

}
