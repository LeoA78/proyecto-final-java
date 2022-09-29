package com.spring.app.mappers.impl;

import com.spring.app.dtos.request.AddressDTO;
import com.spring.app.dtos.response.AddressResponseDTO;
import com.spring.app.entities.Address;
import com.spring.app.mappers.IAddressMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AddressMapperImpl extends Exception implements IAddressMapper {
    private final ModelMapper modelMapper;

    @Override
    public AddressResponseDTO entityToResponseDto(Address address) {
        AddressResponseDTO addressResponseDTO = new AddressResponseDTO();
        modelMapper.map(address, addressResponseDTO);
        return addressResponseDTO;
    }

    @Override
    public Address requestDtoToEntity(AddressDTO requestDto) {
        Address address = new Address();
        modelMapper.map(requestDto, address);
        return address;
    }
}
