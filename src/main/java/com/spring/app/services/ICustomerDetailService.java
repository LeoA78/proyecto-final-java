package com.spring.app.services;

import com.spring.app.dtos.request.CustomerDetailDTO;
import com.spring.app.dtos.response.CustomerDetailResponseDTO;

import java.util.List;

public interface ICustomerDetailService {
    List<CustomerDetailResponseDTO> findAllCustomersDetails();

    CustomerDetailResponseDTO findCustomerDetailById(Long id);

    CustomerDetailResponseDTO addCustomerDetail(CustomerDetailDTO customerDetailDTO);

    CustomerDetailResponseDTO updateCustomerDetail(CustomerDetailDTO customerDetailDTO);

    void deleteCustomerDetailById(Long id);
}
