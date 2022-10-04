package com.spring.app.services;

import com.spring.app.dtos.request.CustomerDTO;
import com.spring.app.dtos.request.CustomerDetailDTO;
import com.spring.app.dtos.request.CustomerWithDetailDTO;
import com.spring.app.dtos.response.CustomerResponseDTO;
import com.spring.app.dtos.response.CustomerWithDetailResponseDTO;

import java.util.List;

public interface ICustomerService {
    List<CustomerResponseDTO> findAllCustomers();

    CustomerResponseDTO findCustomerById(Long id);

    CustomerResponseDTO addCustomer(CustomerDTO customerDTO);

    CustomerWithDetailResponseDTO addCustomerWithDetail(CustomerWithDetailDTO customerWithDetailDTO);

    CustomerResponseDTO updateCustomer(Long id, CustomerDTO customerDTO);

    void deleteCustomerById(Long id);
}
