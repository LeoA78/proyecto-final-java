package com.spring.app.repositories;

import com.spring.app.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<Address, Long> {
}
