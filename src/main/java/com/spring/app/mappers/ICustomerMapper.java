package com.spring.app.mappers;

import com.spring.app.dtos.request.CustomerDTO;
import com.spring.app.dtos.response.CustomerResponseDTO;
import com.spring.app.entities.Customer;

public interface ICustomerMapper {


    CustomerResponseDTO entityToResponseDto(Customer customer);

    Customer requestDtoToEntity(CustomerDTO requestDto);

}
