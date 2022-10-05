package com.spring.app.services;

import com.spring.app.dtos.request.CustomerDTO;
import com.spring.app.dtos.request.FullCustomerDTO;
import com.spring.app.dtos.response.CustomerResponseDTO;
import com.spring.app.dtos.response.FullCustomerResponseDTO;

import java.util.List;

public interface ICustomerService {
    List<CustomerResponseDTO> findAllCustomers();

    CustomerResponseDTO findCustomerById(Long id);

    CustomerResponseDTO addCustomer(CustomerDTO customerDTO);

    FullCustomerResponseDTO addFullCustomer(FullCustomerDTO fullCustomerDTO);

    CustomerResponseDTO updateCustomer(Long id, CustomerDTO customerDTO);

    void deleteCustomerById(Long id);
}
