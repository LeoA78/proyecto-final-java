package com.spring.app.services;

import com.spring.app.dtos.request.CustomerDTO;
import com.spring.app.dtos.request.CustomerUpdateDTO;
import com.spring.app.dtos.response.CustomerResponseDTO;
import com.spring.app.dtos.response.InvoiceResponseDTO;

import java.util.List;

public interface ICustomerService {

    List<CustomerResponseDTO> findAllCustomers();


    CustomerResponseDTO findCustomerById(Long id);

    CustomerResponseDTO addCustomer(CustomerDTO customerDTO);

    CustomerResponseDTO updateCustomer(Long id, CustomerUpdateDTO customerDTO);

    void deleteCustomerById(Long id);

    List<InvoiceResponseDTO> findAllInvoicesById(Long id);
}
