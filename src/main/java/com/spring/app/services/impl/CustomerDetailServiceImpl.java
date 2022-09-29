package com.spring.app.services.impl;

import com.spring.app.dtos.request.CustomerDetailDTO;
import com.spring.app.dtos.response.CustomerDetailResponseDTO;
import com.spring.app.mappers.ICustomerDetailMapper;
import com.spring.app.repositories.ICustomerDetailRepository;
import com.spring.app.services.ICustomerDetailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service //Para que pueda ser inyectado desde otro lugar
@Slf4j
@Transactional //Hace el trabajo de JPA (commit - begin - rollback - etc)
public class CustomerDetailServiceImpl extends Exception implements ICustomerDetailService {

    @Autowired
    private ICustomerDetailRepository customerDetailRepository;

    @Autowired
    private ICustomerDetailMapper customerDetailMapper;

    @Override
    public List<CustomerDetailResponseDTO> findAllCustomersDetails(){
        return null;
    }

    @Override
    public CustomerDetailResponseDTO findCustomerDetailById(Long id){
        return null;
    }

    @Override
    public CustomerDetailResponseDTO addCustomerDetail(CustomerDetailDTO customerDetailDTO){
        return null;
    }

    @Override
    public CustomerDetailResponseDTO updateCustomerDetail(CustomerDetailDTO customerDetailDTO){
        return null;
    }

    @Override
    public void deleteCustomerDetailById(Long id){

    }








}
