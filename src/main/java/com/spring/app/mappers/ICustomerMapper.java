package com.spring.app.mappers;

import com.spring.app.dtos.request.CustomerDTO;
import com.spring.app.dtos.response.CustomerResponseDTO;
import com.spring.app.dtos.response.FullCustomerResponseDTO;
import com.spring.app.entities.Address;
import com.spring.app.entities.Customer;
import com.spring.app.entities.CustomerDetail;

public interface ICustomerMapper {
    CustomerResponseDTO entityToResponseDto(Customer customer);


    FullCustomerResponseDTO entityToFullResponseDto(Customer customer);

    Customer requestDtoToEntity(CustomerDTO requestDto);

}
