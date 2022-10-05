package com.spring.app.services;

import com.spring.app.dtos.request.FullInvoiceDTO;
import com.spring.app.dtos.request.InvoiceDTO;
import com.spring.app.dtos.response.FullInvoiceResponseDTO;
import com.spring.app.dtos.response.InvoiceResponseDTO;

import java.util.List;

public interface IInvoiceService {
    List<InvoiceResponseDTO> findAllInvoices();

    InvoiceResponseDTO findInvoiceById(Long id);

    FullInvoiceResponseDTO addInvoice(FullInvoiceDTO fullInvoiceDTO);

    InvoiceResponseDTO updateInvoice(Long id, InvoiceDTO invoiceDTO);

    void deleteInvoiceById(Long id);
}
