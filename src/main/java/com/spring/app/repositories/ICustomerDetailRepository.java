package com.spring.app.repositories;

import com.spring.app.entities.CustomerDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerDetailRepository extends JpaRepository<CustomerDetail, Long> {
}
