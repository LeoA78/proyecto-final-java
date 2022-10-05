package com.spring.app.mappers;

import com.spring.app.dtos.request.InvoiceDTO;
import com.spring.app.dtos.response.FullInvoiceResponseDTO;
import com.spring.app.dtos.response.InvoiceResponseDTO;
import com.spring.app.entities.Customer;
import com.spring.app.entities.Invoice;

public interface IInvoiceMapper {
    InvoiceResponseDTO entityToResponseDto(Invoice invoice);

    Invoice requestDtoToEntity(InvoiceDTO requestDto);
    FullInvoiceResponseDTO entityToFullInvoice(Invoice invoice);
}
