package com.sb.spring.customerservice.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICustomerRepository extends JpaRepository<CustomerEntity, Integer> {

    Optional<CustomerEntity> findByUserId(String userId);

    @Query("SELECT t FROM CustomerEntity t WHERE t.id IN :ids")
    List<CustomerEntity> findByIdsIn(@Param("ids") List<Integer> ids);
}
