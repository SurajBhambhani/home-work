package com.sb.spring.customerservice.customer;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String userId;
    private String firstName;
    private String lastName;
    private String email;

    @Column(name = "phone_number")
    private String phone;
}