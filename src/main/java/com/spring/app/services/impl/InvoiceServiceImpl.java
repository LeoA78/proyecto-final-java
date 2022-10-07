package com.spring.app.services.impl;

import com.spring.app.dtos.request.InvoiceDTO;
import com.spring.app.dtos.request.InvoiceUpdateDTO;
import com.spring.app.dtos.response.InvoiceResponseDTO;
import com.spring.app.entities.Customer;
import com.spring.app.entities.Invoice;
import com.spring.app.exceptions.BadRequestException;
import com.spring.app.mappers.IInvoiceMapper;
import com.spring.app.repositories.ICustomerRepository;
import com.spring.app.repositories.IInvoiceRepository;
import com.spring.app.services.IInvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service //Para que pueda ser inyectado desde otro lugar
@Transactional //Hace el trabajo de JPA (commit - begin - rollback - etc)
public class InvoiceServiceImpl implements IInvoiceService {

    @Autowired
    private IInvoiceRepository invoiceRepository;

    @Autowired
    private IInvoiceMapper invoiceMapper;

    @Autowired
    private ICustomerRepository customerRepository;

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
            throw new BadRequestException("the id cannot be a negative number. Request ID:" + id);
        }

        Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);

        if (optionalInvoice.isEmpty()) {
            throw new IllegalStateException("Record with id " + id + " does not exist.");
        }

        return invoiceMapper.entityToResponseDto(optionalInvoice.get());
    }

    /**
     * This method adds an invoice to the database and returns the added invoice.
     *
     * @param invoiceDTO Invoice Request DTO
     * @return fullInvoiceResponseDTO
     */
    @Override
    public InvoiceResponseDTO addInvoice(InvoiceDTO invoiceDTO) {
        Double totalInvoice = invoiceDTO.getTotal();

        if(totalInvoice <= 0 ){
            throw new BadRequestException("Total cannot be zero or negative");
        }

        Customer customerByDni = customerRepository.findByDni(invoiceDTO.getCustomerDni());

        if(customerByDni == null){
            throw new BadRequestException("Customer doesn't exist");
        }

        Invoice invoiceToCreate = invoiceMapper.requestDtoToEntity(invoiceDTO);
        invoiceToCreate.setCreatedDate(LocalDate.now());
        invoiceToCreate.setCustomer(customerByDni);

        Invoice createdInvoice = invoiceRepository.save(invoiceToCreate);

        return invoiceMapper.entityToResponseDto(createdInvoice);
    }

    /**
     * This method update an invoice in to the database and returns the modified invoice.
     *
     * @param id          Invoice id
     * @param invoiceDTO Invoice Request DTO
     * @return InvoiceResponseDTO
     */
    @Override
    public InvoiceResponseDTO updateInvoice(Long id, InvoiceUpdateDTO invoiceDTO) {

        if(invoiceDTO.getTotal() <= 0 ){
            throw new BadRequestException("Total cannot be zero or negative");
        }

        //AGREGAR TIPOS DE FACTURA

        if(Objects.equals(invoiceDTO.getDescription(), "")){
            throw new BadRequestException("Description cannot be empty");
        }

        Optional<Invoice> optionalEntity = invoiceRepository.findById(id);

        if (optionalEntity.isEmpty()) {
            throw new BadRequestException("Invoice to update not found");
        }

        Invoice invoiceToUpdate = invoiceMapper.requestDtoToEntity(invoiceDTO);

        invoiceToUpdate.setIdInvoice(id);
        invoiceToUpdate.setCreatedDate(optionalEntity.get().getCreatedDate());
        invoiceToUpdate.setCustomer(optionalEntity.get().getCustomer());

        Invoice updatedInvoice = invoiceRepository.save(invoiceToUpdate);

        return invoiceMapper.entityToResponseDto(updatedInvoice);
    }

    /**
     * This method delete an invoice in to the database.
     *
     * @param id Invoice id
     */
    @Override
    public void deleteInvoiceById(Long id) {

        if(id <= 0 ){
            throw new BadRequestException("Id cannot be zero or negative");
        }

        Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);



        if (optionalInvoice.isEmpty()) {
            throw new RuntimeException("Record with id " + id + " does not exist.");
        }

        invoiceRepository.delete(optionalInvoice.get());

    }

}