package com.spring.app.mappers;

import com.spring.app.dtos.request.InvoiceDTO;
import com.spring.app.dtos.request.InvoiceUpdateDTO;
import com.spring.app.dtos.response.InvoiceResponseDTO;
import com.spring.app.dtos.response.InvoiceWithoutCustomerResponseDTO;
import com.spring.app.entities.Invoice;

public interface IInvoiceMapper {
    Invoice requestDtoToEntity(InvoiceUpdateDTO requestDto);

    Invoice requestDtoToEntity(InvoiceDTO requestDto);

    InvoiceResponseDTO entityToResponseDto(Invoice invoice);

    InvoiceWithoutCustomerResponseDTO entityToInvoiceWithoutCustomerResponseDto(Invoice invoice);
}
