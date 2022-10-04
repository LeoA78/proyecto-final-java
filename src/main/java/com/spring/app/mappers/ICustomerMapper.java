package com.spring.app.mappers;

import com.spring.app.dtos.request.CustomerDTO;
import com.spring.app.dtos.response.CustomerResponseDTO;
import com.spring.app.dtos.response.CustomerWithDetailResponseDTO;
import com.spring.app.entities.Customer;
import com.spring.app.entities.CustomerDetail;

public interface ICustomerMapper {
    CustomerResponseDTO entityToResponseDto(Customer customer);

    Customer requestDtoToEntity(CustomerDTO requestDto);

    CustomerWithDetailResponseDTO entitiesToCustomerWithDetailResponseDto(Customer customer, CustomerDetail customerDetail);
}
