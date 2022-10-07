package com.spring.app.mappers;

import com.spring.app.dtos.request.AddressDTO;
import com.spring.app.dtos.request.AddressWithCustomerDniDTO;
import com.spring.app.dtos.response.AddressResponseDTO;
import com.spring.app.entities.Address;

public interface IAddressMapper {
    AddressResponseDTO entityToResponseDto(Address address);

    Address requestDtoToEntity(AddressWithCustomerDniDTO requestDto);

    Address requestDtoToEntity(AddressDTO requestDto);


    //PersonResponseDTOFull listPersonDTOs(List<PersonEntity> listPersonEntities);

}
