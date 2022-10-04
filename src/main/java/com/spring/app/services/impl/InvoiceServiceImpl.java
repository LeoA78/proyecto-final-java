package com.spring.app.services.impl;

import com.spring.app.dtos.request.InvoiceDTO;
import com.spring.app.dtos.response.InvoiceResponseDTO;
import com.spring.app.entities.Invoice;
import com.spring.app.exceptions.BadRequestException;
import com.spring.app.mappers.IInvoiceMapper;
import com.spring.app.repositories.IInvoiceRepository;
import com.spring.app.services.IInvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service //Para que pueda ser inyectado desde otro lugar
@Transactional //Hace el trabajo de JPA (commit - begin - rollback - etc)
public class InvoiceServiceImpl implements IInvoiceService {

    @Autowired
    private IInvoiceRepository invoiceRepository;

    @Autowired
    private IInvoiceMapper invoiceMapper;

    /**
     * This method return all invoices
     *
     * @return List<InvoiceResponseDTO>
     */
    @Override
    public List<InvoiceResponseDTO> findAllInvoices() {
        List<InvoiceResponseDTO> invoiceResponseDTOS = new ArrayList<>();

        List<Invoice> invoiceList = invoiceRepository.findAll();

        for (Invoice invoice : invoiceList) {
            invoiceResponseDTOS.add(invoiceMapper.entityToResponseDto(invoice));
        }

        return invoiceResponseDTOS;
    }

    /**
     * This method return one invoice
     *
     * @param id
     * @return InvoiceResponseDTO
     */
    @Override
    public InvoiceResponseDTO findInvoiceById(Long id) {

        if (id < 0) {
            throw new BadRequestException("El id no puede ser un número negativo.");
        }

        InvoiceResponseDTO invoiceResponseDTO;

        Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);

        if (optionalInvoice.isEmpty()) {
            throw new IllegalStateException("El registro con el id " + id + " no existe.");
        }

        invoiceResponseDTO = invoiceMapper.entityToResponseDto(optionalInvoice.get());

        return invoiceResponseDTO;
    }

    /**
     * This method adds an invoice to the database and returns the added invoice.
     *
     * @param invoiceDTO Invoice Request DTO
     * @return InvoiceResponseDTO
     */
    @Override
    public InvoiceResponseDTO addInvoice(InvoiceDTO invoiceDTO) {
        InvoiceResponseDTO invoiceResponseDTO;

        Invoice invoiceEntity = invoiceMapper.requestDtoToEntity(invoiceDTO);
        invoiceEntity.setCreatedDate(LocalDate.now());

        Invoice savedInvoice = invoiceRepository.save(invoiceEntity);

        invoiceResponseDTO = invoiceMapper.entityToResponseDto(savedInvoice);

        return invoiceResponseDTO;
    }

    /**
     * This method update an invoice in to the database and returns the modified invoice.
     *
     * @param id          Invoice id
     * @param invoiceDTO Invoice Request DTO
     * @return InvoiceResponseDTO
     */
    @Override
    public InvoiceResponseDTO updateInvoice(Long id, InvoiceDTO invoiceDTO) {

        Optional<Invoice> optionalEntity = invoiceRepository.findById(id);

        if (optionalEntity.isEmpty()) {
            throw new RuntimeException("Error no existe el id de persona buscado");
        }

        Invoice invoice = invoiceMapper.requestDtoToEntity(invoiceDTO);

        invoice.setIdInvoice(id);
        invoice.setCreatedDate(optionalEntity.get().getCreatedDate());

        Invoice updatedInvoice = invoiceRepository.save(invoice);

        return invoiceMapper.entityToResponseDto(updatedInvoice);
    }

    /**
     * This method delete an invoice in to the database.
     *
     * @param id Invoice id
     */
    @Override
    public void deleteInvoiceById(Long id) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);

        if (optionalInvoice.isEmpty()) {
            throw new RuntimeException("Error no existe el id buscado");
        }

        invoiceRepository.delete(optionalInvoice.get());
        System.out.println("La factura con el " + id + " fue eliminada correctamente.");

    }

}