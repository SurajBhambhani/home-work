package com.sb.spring.loanapplicationservice.entity;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "LOAN")

public class LoanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String customerId;
    private Integer amount; // TODO To be changed to more appropriate format
    private String duration; // TODO To be changed to more appropriate format
    private String status;
}