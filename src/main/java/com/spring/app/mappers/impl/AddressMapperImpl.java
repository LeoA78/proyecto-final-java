package com.spring.app.mappers.impl;

import com.spring.app.dtos.response.AddressResponseDTO;
import com.spring.app.entities.AddressEntity;
import com.spring.app.mappers.IMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AddressMapperImpl extends Exception implements IMapper {
    private final ModelMapper modelMapper;

    @Override
    public AddressResponseDTO entityToResponseDto(Object addressEntity) {
        AddressResponseDTO personResponseDTO = new AddressResponseDTO();
        modelMapper.map(addressEntity, personResponseDTO);
        return personResponseDTO;
    }

    @Override
    public AddressEntity requestDtoToEntity(Object requestDto) {
        AddressEntity addressEntity = new AddressEntity();
        modelMapper.map(requestDto, addressEntity);
        return addressEntity;
    }
}
