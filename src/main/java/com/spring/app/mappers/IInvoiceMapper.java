package com.spring.app.mappers;

import com.spring.app.dtos.request.InvoiceDTO;
import com.spring.app.dtos.response.InvoiceResponseDTO;
import com.spring.app.entities.Invoice;

public interface IInvoiceMapper {
    InvoiceResponseDTO entityToResponseDto(Invoice invoice);

    Invoice requestDtoToEntity(InvoiceDTO requestDto);
}
