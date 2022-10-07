package com.spring.app.mappers.impl;

import com.spring.app.dtos.request.AddressDTO;
import com.spring.app.dtos.request.AddressWithCustomerDniDTO;
import com.spring.app.dtos.response.AddressResponseDTO;
import com.spring.app.entities.Address;
import com.spring.app.mappers.IAddressMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AddressMapperImpl implements IAddressMapper {
    private final ModelMapper modelMapper;

    @Override
    public AddressResponseDTO entityToResponseDto(Address address) {
        return modelMapper.map(address, AddressResponseDTO.class);
    }

    @Override
    public Address requestDtoToEntity(AddressWithCustomerDniDTO requestDto) {
        return modelMapper.map(requestDto, Address.class);
    }

    @Override
    public Address requestDtoToEntity(AddressDTO requestDto) {
        return modelMapper.map(requestDto, Address.class);
    }
}
