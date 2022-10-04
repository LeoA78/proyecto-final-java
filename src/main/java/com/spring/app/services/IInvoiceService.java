package com.spring.app.services;

import com.spring.app.dtos.request.InvoiceDTO;
import com.spring.app.dtos.response.InvoiceResponseDTO;

import java.util.List;

public interface IInvoiceService {
    List<InvoiceResponseDTO> findAllInvoices();

    InvoiceResponseDTO findInvoiceById(Long id);

    InvoiceResponseDTO addInvoice(InvoiceDTO invoiceDTO);

    InvoiceResponseDTO updateInvoice(Long id, InvoiceDTO invoiceDTO);

    void deleteInvoiceById(Long id);
}
