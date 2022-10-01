package com.spring.app.mappers.impl;

import com.spring.app.dtos.request.CustomerDetailDTO;
import com.spring.app.dtos.response.CustomerDetailResponseDTO;
import com.spring.app.entities.CustomerDetail;
import com.spring.app.mappers.ICustomerDetailMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomerDetailMapperImpl implements ICustomerDetailMapper {
    private final ModelMapper modelMapper;

    @Override
    public CustomerDetailResponseDTO entityToResponseDto(CustomerDetail customerDetail) {
        CustomerDetailResponseDTO customerDetailResponseDTO = new CustomerDetailResponseDTO();
        modelMapper.map(customerDetail, customerDetailResponseDTO);
        return customerDetailResponseDTO;
    }

    @Override
    public CustomerDetail requestDtoToEntity(CustomerDetailDTO requestDto) {
        CustomerDetail customerDetail = new CustomerDetail();
        modelMapper.map(requestDto, customerDetail);
        return customerDetail;
    }
}
