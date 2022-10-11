package com.spring.app.mappers;

import com.spring.app.dtos.request.CustomerDTO;
import com.spring.app.dtos.request.CustomerUpdateDTO;
import com.spring.app.dtos.response.CustomerResponseDTO;
import com.spring.app.entities.Customer;

public interface ICustomerMapper {


    Customer requestDtoToEntity(CustomerUpdateDTO requestDto);

    Customer responseDtoToEntity(CustomerResponseDTO responseDto);

    CustomerResponseDTO entityToResponseDto(Customer customer);

    Customer requestDtoToEntity(CustomerDTO requestDto);

}
