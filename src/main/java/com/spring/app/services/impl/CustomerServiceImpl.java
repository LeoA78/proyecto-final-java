package com.spring.app.services.impl;

import com.spring.app.dtos.request.CustomerDTO;
import com.spring.app.dtos.response.CustomerResponseDTO;
import com.spring.app.mappers.ICustomerMapper;
import com.spring.app.repositories.ICustomerRepository;
import com.spring.app.services.ICustomerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private ICustomerMapper customerMapper;

    @Override
    public List<CustomerResponseDTO> findAllCustomers(){
        return null;
    }

    @Override
    public CustomerResponseDTO findCustomerById(Long id){
        return null;
    }

    @Override
    public CustomerResponseDTO addCustomer(CustomerDTO customerDTO){
        return null;
    }

    @Override
    public CustomerResponseDTO updateCustomer(CustomerDTO customerDTO){
        return null;
    }

    @Override
    public void deleteCustomerById(Long id){

    }








}
