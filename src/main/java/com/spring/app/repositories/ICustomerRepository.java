package com.spring.app.repositories;

import com.spring.app.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByDni(String dni);

}
