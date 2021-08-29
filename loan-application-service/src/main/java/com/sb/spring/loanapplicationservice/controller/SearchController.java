package com.sb.spring.loanapplicationservice.controller;

import com.sb.spring.loanapplicationservice.service.ILoanApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SearchController {

    @Autowired
    ILoanApplicationService loanApplicationService;

    @GetMapping("/loanapplications/search")
    public ResponseEntity<?> getAllLoansByCustomerId(@RequestParam Integer minAmount, @RequestParam Integer maxAmount, @RequestParam String status) {
        return ResponseEntity.ok().body(loanApplicationService.searchCustomers(minAmount, maxAmount, status));
    }
}
