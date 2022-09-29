package com.spring.app.services;

import com.spring.app.dtos.request.CustomerDTO;
import com.spring.app.dtos.response.CustomerResponseDTO;

import java.util.List;

public interface ICustomerService {
    List<CustomerResponseDTO> findAllCustomers();

    CustomerResponseDTO findCustomerById(Long id);

    CustomerResponseDTO addCustomer(CustomerDTO customerDTO);

    CustomerResponseDTO updateCustomer(CustomerDTO customerDTO);

    void deleteCustomerById(Long id);
}
