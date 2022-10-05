package com.spring.app.mappers.impl;

import com.spring.app.dtos.request.CustomerDTO;
import com.spring.app.dtos.response.AddressResponseDTO;
import com.spring.app.dtos.response.CustomerDetailResponseDTO;
import com.spring.app.dtos.response.CustomerResponseDTO;
import com.spring.app.dtos.response.FullCustomerResponseDTO;
import com.spring.app.entities.Address;
import com.spring.app.entities.Customer;
import com.spring.app.entities.CustomerDetail;
import com.spring.app.mappers.ICustomerMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
    public FullCustomerResponseDTO entitiesToFullCustomerResponseDto(Customer customer, CustomerDetail customerDetail, Address address) {

        CustomerDetailResponseDTO customerDetailResponseDTO = new CustomerDetailResponseDTO();
        modelMapper.map(customerDetail, customerDetailResponseDTO);

        AddressResponseDTO addressResponseDTO = new AddressResponseDTO();
        modelMapper.map(address,addressResponseDTO);

        List<AddressResponseDTO> addressResponseDTOList = new ArrayList<>();
        addressResponseDTOList.add(addressResponseDTO);


        return FullCustomerResponseDTO.builder()
                .idCustomer(customer.getIdCustomer())
                .name(customer.getName())
                .lastName(customer.getLastName())
                .dni(customer.getDni())
                .dateOfBirth(customer.getDateOfBirth())
                .createdDate(customer.getDateOfBirth())
                .detail(customerDetailResponseDTO)
                .addresses(addressResponseDTOList)
                .build();

    }

}
