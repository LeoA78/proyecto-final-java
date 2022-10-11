package com.spring.app.services.impl;

import com.spring.app.dtos.request.CustomerDTO;
import com.spring.app.dtos.request.CustomerUpdateDTO;
import com.spring.app.dtos.response.CustomerResponseDTO;
import com.spring.app.dtos.response.InvoiceWithoutCustomerResponseDTO;
import com.spring.app.entities.Address;
import com.spring.app.entities.Customer;
import com.spring.app.entities.CustomerDetail;
import com.spring.app.entities.Invoice;
import com.spring.app.exceptions.BadRequestException;
import com.spring.app.mappers.IAddressMapper;
import com.spring.app.mappers.ICustomerDetailMapper;
import com.spring.app.mappers.ICustomerMapper;
import com.spring.app.mappers.IInvoiceMapper;
import com.spring.app.repositories.ICustomerRepository;
import com.spring.app.services.ICustomerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service //Para que pueda ser inyectado desde otro lugar
@Transactional //Hace el trabajo de JPA (commit - begin - rollback - etc)
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepository;
    @Autowired
    private ICustomerMapper customerMapper;

    @Autowired
    private ICustomerDetailMapper customerDetailMapper;

    @Autowired
    private IAddressMapper addressMapper;

    @Autowired
    private IInvoiceMapper invoiceMapper;

    /**
     * This method return all customers
     *
     * @return List<CustomerResponseDTO>
     */
    @Override
    public List<CustomerResponseDTO> findAllCustomers() {
        List<CustomerResponseDTO> customerResponseDTOS = new ArrayList<>();

        List<Customer> customerList = customerRepository.findAll();

        for (Customer customer : customerList) {
            customerResponseDTOS.add(customerMapper.entityToResponseDto(customer));
        }

        return customerResponseDTOS;
    }

    /**
     * This method return one customer
     *
     * @param id
     * @return FullCustomerResponseDTO
     */
    @Override
    public CustomerResponseDTO findCustomerById(Long id) {

        if (id < 0) {
            throw new BadRequestException("the id cannot be a negative number. Request ID:" + id);
        }

        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isEmpty()) {
            throw new IllegalStateException("Record with id " + id + " does not exist.");
        }

        return customerMapper.entityToResponseDto(optionalCustomer.get());
    }


    /**
     * This method adds a customer with detail to the database and returns the added customer.
     *
     * @param customerDTO Customer With Detail Request DTO
     * @return CustomerResponseDTO
     */
    @Override
    public CustomerResponseDTO addCustomer(CustomerDTO customerDTO) {

        if (ObjectUtils.isEmpty(customerDTO)) {
            throw new BadRequestException("Empty data in the entered entity");
        }

        Customer customerByDni = customerRepository.findByDni(customerDTO.getDni());

        if (customerByDni != null) {
            throw new BadRequestException("the client with the DNI entered already exists");
        }

        Customer customerToCreate = customerMapper.requestDtoToEntity(customerDTO);
        CustomerDetail customerDetail = customerDetailMapper.requestDtoToEntity(customerDTO.getDetail());

        Address address = addressMapper.requestDtoToEntity(customerDTO.getAddress());

        customerToCreate.setCreatedDate(LocalDate.now());
        customerToCreate.setCustomerDetail(customerDetail);

        customerToCreate.addAddress(address);

        address.setCustomer(customerToCreate);

        Customer customerCreated = customerRepository.save(customerToCreate);

        return customerMapper.entityToResponseDto(customerCreated);
    }

    /**
     * This method update a customer in to the database and returns the modified customer.
     *
     * @param id          Customer id
     * @param customerDTO Customer Request DTO
     * @return CustomerResponseDTO
     */
    @Override
    public CustomerResponseDTO updateCustomer(Long id, CustomerUpdateDTO customerDTO) {

        Optional<Customer> optionalEntity = customerRepository.findById(id);

        if (optionalEntity.isEmpty()) {
            throw new RuntimeException("Customer to update not found");
        }

        Customer customerByDni = customerRepository.findByDni(customerDTO.getDni());

        //Si los DNI son distintos y se encontró un cliente con el DNI nuevo
        if (!Objects.equals(customerDTO.getDni(), optionalEntity.get().getDni()) && customerByDni != null) {
            throw new BadRequestException("the client with the DNI entered already exists");
        }


        Customer customer = customerMapper.requestDtoToEntity(customerDTO);

        customer.setIdCustomer(id);
        customer.setCreatedDate(optionalEntity.get().getCreatedDate());
        customer.setCustomerDetail(optionalEntity.get().getCustomerDetail());
        customer.setAddressList(optionalEntity.get().getAddressList());


        Customer updatedCustomer = customerRepository.save(customer);

        return customerMapper.entityToResponseDto(updatedCustomer);
    }

    /**
     * This method delete a customer in to the database.
     *
     * @param id Customer id
     */
    @Override
    public void deleteCustomerById(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isEmpty()) {
            throw new RuntimeException("Customer to delete not found");
        }

        if (!optionalCustomer.get().getInvoiceList().isEmpty()) {
            throw new RuntimeException("Customer can not be deleted if it has invoices");
        }

        customerRepository.delete(optionalCustomer.get());
    }

    @Override
    public List<InvoiceWithoutCustomerResponseDTO> findAllInvoicesById(Long id){

        List<InvoiceWithoutCustomerResponseDTO> invoicesList = new ArrayList<>();

        if (id < 0) {
            throw new BadRequestException("the id cannot be a negative number. Request ID:" + id);
        }

        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isEmpty()) {
            throw new IllegalStateException("Record with id " + id + " does not exist.");
        }

        for(Invoice invoice : optionalCustomer.get().getInvoiceList()){
            invoicesList.add(invoiceMapper.entityToInvoiceWithoutCustomerResponseDto(invoice));
        }

        return invoicesList;

    }


    @Override
    public CustomerResponseDTO findCustomerByDni(String dni) {
        Customer customer = customerRepository.findByDni(dni);

        if (customer == null) {
            throw new BadRequestException("Customer with dni " + dni + " does not exist");
        }

        if (Objects.equals(dni, "")) {
            throw new BadRequestException("Dni cannot be empty.");
        }

        return customerMapper.entityToResponseDto(customer);
    }

}
