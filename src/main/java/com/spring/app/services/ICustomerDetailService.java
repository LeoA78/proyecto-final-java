package com.spring.app.services;

import com.spring.app.dtos.request.CustomerDetailDTO;
import com.spring.app.dtos.response.CustomerDetailResponseDTO;

import java.util.List;

public interface ICustomerDetailService {
    List<CustomerDetailResponseDTO> findAllCustomerDetails();

    CustomerDetailResponseDTO findCustomerDetailById(Long id);

    CustomerDetailResponseDTO updateCustomerDetail(Long id, CustomerDetailDTO customerDetailDTO);

}
