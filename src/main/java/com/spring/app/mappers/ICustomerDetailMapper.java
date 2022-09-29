package com.spring.app.mappers;

import com.spring.app.dtos.request.CustomerDetailDTO;
import com.spring.app.dtos.response.CustomerDetailResponseDTO;
import com.spring.app.entities.CustomerDetail;

public interface ICustomerDetailMapper {
    CustomerDetailResponseDTO entityToResponseDto(CustomerDetail customerDetail);

    CustomerDetail requestDtoToEntity(CustomerDetailDTO requestDto);
}
