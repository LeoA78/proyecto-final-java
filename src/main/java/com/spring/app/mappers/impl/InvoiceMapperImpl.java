package com.spring.app.mappers.impl;

import com.spring.app.dtos.request.InvoiceDTO;
import com.spring.app.dtos.response.InvoiceResponseDTO;
import com.spring.app.entities.Invoice;
import com.spring.app.mappers.IInvoiceMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class InvoiceMapperImpl implements IInvoiceMapper {
    private final ModelMapper modelMapper;

    @Override
    public InvoiceResponseDTO entityToResponseDto(Invoice invoice) {
        InvoiceResponseDTO invoiceResponseDTO = new InvoiceResponseDTO();
        modelMapper.map(invoice, invoiceResponseDTO);
        return invoiceResponseDTO;
    }

    @Override
    public Invoice requestDtoToEntity(InvoiceDTO requestDto) {
        Invoice invoice = new Invoice();
        modelMapper.map(requestDto, invoice);
        return invoice;
    }
}
