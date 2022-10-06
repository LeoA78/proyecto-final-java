package com.spring.app.mappers.impl;

import com.spring.app.dtos.request.InvoiceDTO;
import com.spring.app.dtos.response.*;
import com.spring.app.entities.Invoice;
import com.spring.app.mappers.ICustomerDetailMapper;
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
        Invoice invoice = new Invoice();
        modelMapper.map(requestDto, invoice);
        return invoice;
    }

    @Override
    public InvoiceResponseDTO entityToResponseDto(Invoice invoice) {
        FullCustomerResponseDTO fullCustomerResponseDTO = customerMapper.entityToFullResponseDto(invoice.getCustomer());

        return InvoiceResponseDTO.builder()
                .idInvoice(invoice.getIdInvoice())
                .invoiceType(invoice.getInvoiceType())
                .description(invoice.getDescription())
                .createdDate(invoice.getCreatedDate())
                .total(invoice.getTotal())
                .customer(fullCustomerResponseDTO)
                .build();
    }
}
