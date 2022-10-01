package com.spring.app.services.impl;

import com.spring.app.dtos.request.InvoiceDTO;
import com.spring.app.dtos.response.InvoiceResponseDTO;
import com.spring.app.mappers.IInvoiceMapper;
import com.spring.app.repositories.IInvoiceRepository;
import com.spring.app.services.IInvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
@Transactional
public class InvoiceServiceImpl implements IInvoiceService {

    @Autowired
    private IInvoiceRepository invoiceRepository;

    @Autowired
    private IInvoiceMapper invoiceMapper;

    @Override
    public List<InvoiceResponseDTO> findAllInvoices(){
        return null;
    }

    @Override
    public InvoiceResponseDTO findInvoiceById(Long id){
        return null;
    }

    @Override
    public InvoiceResponseDTO addInvoice(InvoiceDTO invoiceDTO){
        return null;
    }

    @Override
    public InvoiceResponseDTO updateInvoice(InvoiceDTO invoiceDTO){
        return null;
    }

    @Override
    public void deleteInvoiceById(Long id){

    }








}
