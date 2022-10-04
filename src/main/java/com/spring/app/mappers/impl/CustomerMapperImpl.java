package com.spring.app.mappers.impl;

import com.spring.app.dtos.request.CustomerDTO;
import com.spring.app.dtos.response.CustomerDetailResponseDTO;
import com.spring.app.dtos.response.CustomerResponseDTO;
import com.spring.app.dtos.response.CustomerWithDetailResponseDTO;
import com.spring.app.entities.Customer;
import com.spring.app.entities.CustomerDetail;
import com.spring.app.mappers.ICustomerMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomerMapperImpl implements ICustomerMapper {
    private final ModelMapper modelMapper;

    @Override
    public CustomerResponseDTO entityToResponseDto(Customer customer) {
        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
        modelMapper.map(customer, customerResponseDTO);
        return customerResponseDTO;
    }

    @Override
    public Customer requestDtoToEntity(CustomerDTO requestDto) {
        Customer customer = new Customer();
        modelMapper.map(requestDto, customer);
        return customer;
    }

    @Override
    public CustomerWithDetailResponseDTO entitiesToCustomerWithDetailResponseDto(Customer customer, CustomerDetail customerDetail) {

        CustomerDetailResponseDTO customerDetailResponseDTO = new CustomerDetailResponseDTO();
        modelMapper.map(customerDetail, customerDetailResponseDTO);

        return CustomerWithDetailResponseDTO.builder()
                .idCustomer(customer.getIdCustomer())
                .name(customer.getName())
                .lastName(customer.getLastName())
                .dni(customer.getDni())
                .dateOfBirth(customer.getDateOfBirth())
                .createdDate(customer.getDateOfBirth())
                .detail(customerDetailResponseDTO)
                .build();
    }

}
