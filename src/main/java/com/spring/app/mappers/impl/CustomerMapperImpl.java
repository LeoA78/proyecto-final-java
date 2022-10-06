package com.spring.app.mappers.impl;

import com.spring.app.dtos.request.AddressDTO;
import com.spring.app.dtos.request.CustomerDTO;
import com.spring.app.dtos.response.AddressResponseDTO;
import com.spring.app.dtos.response.CustomerDetailResponseDTO;
import com.spring.app.dtos.response.CustomerResponseDTO;
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
    public Customer requestDtoToEntity(CustomerDTO requestDto) {
        Customer customer = new Customer();
        modelMapper.map(requestDto, customer);
        return customer;
    }

    @Override
    public CustomerResponseDTO entityToResponseDto(Customer customer) {
        CustomerDetailResponseDTO customerDetailResponseDTO = new CustomerDetailResponseDTO();
        modelMapper.map(customer.getCustomerDetail(), customerDetailResponseDTO);


        List<AddressResponseDTO> addressResponseDTOList = new ArrayList<>();
        AddressResponseDTO addressResponseDTO = new AddressResponseDTO();

        for(Address address :customer.getAddressList()) {
            modelMapper.map(address,addressResponseDTO);
            addressResponseDTOList.add(addressResponseDTO);
        }

        return CustomerResponseDTO.builder()
                .idCustomer(customer.getIdCustomer())
                .name(customer.getName())
                .lastName(customer.getLastName())
                .dni(customer.getDni())
                .dateOfBirth(customer.getDateOfBirth())
                .createdDate(customer.getCreatedDate())
                .detail(customerDetailResponseDTO)
                .addresses(addressResponseDTOList)
                .build();
    }





}
