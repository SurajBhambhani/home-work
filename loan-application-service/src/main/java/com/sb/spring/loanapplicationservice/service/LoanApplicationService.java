package com.sb.spring.loanapplicationservice.service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.sb.spring.loanapplicationservice.entity.LoanEntity;
import com.sb.spring.loanapplicationservice.model.*;
import com.sb.spring.loanapplicationservice.repository.ILoanApplicationRepository;
import com.sb.spring.loanapplicationservice.util.LoanStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoanApplicationService implements ILoanApplicationService {

    //TODO Move to properties
    public static final String CUSTOMER_SERVICE = "customer-service";

    @Autowired
    ILoanApplicationRepository loanApplicationRepository;

    @Autowired
    private EurekaClient discoveryClient;

    @Override
    public List<LoanApplicationDTO> getAllLoans() {
        return loanApplicationRepository.findAll().stream().map(loanEntity ->
                new LoanApplicationDTO(loanEntity.getId(),
                        loanEntity.getCustomerId(),
                        loanEntity.getAmount(),
                        loanEntity.getDuration(),
                        loanEntity.getStatus())).collect(Collectors.toList());
    }

    @Override
    public Integer createLoanApplication(LoanApplicationDTO loanApplicationDTO) {

        log.info("createLoanApplication: Creating loan application, customer id : {}", loanApplicationDTO.getCustomerId());

        LoanEntity loanEntity = new LoanEntity();
        BeanUtils.copyProperties(loanApplicationDTO, loanEntity);
        loanEntity.setStatus(LoanStatus.CREATED.name());

        Integer loanId = loanApplicationRepository.save(loanEntity).getId();
        log.info("createLoanApplication: Loan application created, loan id : {}", loanId);

        return loanId;
    }

    @Override
    public CustomerLoanOutDTO getLoansByCustomerID(Integer customerId) {

        // prepare url call
        StringBuilder url = new StringBuilder(serviceUrl())
                .append("/api/customers/").append(customerId);
        // get customer information
        CustomerDTO customerInformation = restTemplate().exchange(url.toString(), HttpMethod.GET, null, CustomerDTO.class).getBody();

        log.info("getLoansByCustomerID: called customer-service api to get customer details, customer id : {}", customerId);

        List<LoanDTO> loanEntries = loanApplicationRepository.findByCustomerId(customerId.toString()).parallelStream()
                .map(loanEntity -> new LoanDTO(loanEntity.getId(), loanEntity.getAmount(), Integer.parseInt(loanEntity.getDuration()), loanEntity.getStatus())).collect(Collectors.toList());

        log.info("getLoansByCustomerID: called laonapplications repository to get loans, customer id : {}", customerId);

        return new CustomerLoanOutDTO(loanEntries, customerInformation);
    }

    /**
     * TODO Its just a basic flow, positive scenario works.
     *
     * @param minAmount
     * @param maxAmount
     * @param status
     * @return
     */
    @Override
    public List<CustomerSearchDTO> searchCustomers(Integer minAmount, Integer maxAmount, String status) {

        // Get loans between criteria
        log.info("searchCustomers: Get loans between criteria");
        List<Integer> customerIds = loanApplicationRepository.findByStatusAndAmountBetween(status, minAmount, maxAmount)
                .parallelStream().map(loanEntity -> new Integer(loanEntity.getCustomerId())).distinct().collect(Collectors.toList());

        if (customerIds.isEmpty())
            return null;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<List<Integer>> requestEntity = new HttpEntity<>(customerIds,
                headers);
        // prepare url call
        StringBuilder url = new StringBuilder(serviceUrl())
                .append("api/customers/byid/");

        log.info("searchCustomers: Get customer information");
        // get customer information
        List<CustomerSearchDTO> customerInformation = restTemplate().exchange(url.toString(), HttpMethod.POST, requestEntity, new ParameterizedTypeReference<List<CustomerSearchDTO>>() {
        }, 1).getBody();

        return customerInformation;
    }

    // TODO Move to utility
    private String serviceUrl() {
        InstanceInfo instance = discoveryClient.getNextServerFromEureka(CUSTOMER_SERVICE, false);
        return instance.getHomePageUrl();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
