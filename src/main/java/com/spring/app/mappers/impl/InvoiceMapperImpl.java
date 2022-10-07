package com.spring.app.mappers.impl;

import com.spring.app.dtos.request.InvoiceDTO;
import com.spring.app.dtos.response.*;
import com.spring.app.entities.Invoice;
import com.spring.app.mappers.ICustomerMapper;
import com.spring.app.mappers.IInvoiceMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class InvoiceMapperImpl implements IInvoiceMapper {
    private final ModelMapper modelMapper;
    @Autowired
    private ICustomerMapper customerMapper;


    @Override
    public Invoice requestDtoToEntity(InvoiceDTO requestDto) {
        return modelMapper.map(requestDto, Invoice.class);
    }

    @Override
    public InvoiceResponseDTO entityToResponseDto(Invoice invoice) {
        CustomerResponseDTO customerResponseDTO = customerMapper.entityToResponseDto(invoice.getCustomer());

        return InvoiceResponseDTO.builder()
                .idInvoice(invoice.getIdInvoice())
                .invoiceType(invoice.getInvoiceType())
                .description(invoice.getDescription())
                .createdDate(invoice.getCreatedDate())
                .total(invoice.getTotal())
                .customer(customerResponseDTO)
                .build();
    }
}
