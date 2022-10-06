package com.spring.app.services.impl;

import com.spring.app.dtos.request.AddressDTO;
import com.spring.app.dtos.request.CustomerDTO;
import com.spring.app.dtos.response.CustomerResponseDTO;
import com.spring.app.entities.Address;
import com.spring.app.entities.Customer;
import com.spring.app.entities.CustomerDetail;
import com.spring.app.exceptions.BadRequestException;
import com.spring.app.mappers.IAddressMapper;
import com.spring.app.mappers.ICustomerDetailMapper;
import com.spring.app.mappers.ICustomerMapper;
import com.spring.app.repositories.IAddressRepository;
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
    private IAddressRepository addressRepository;

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
            throw new BadRequestException("El id no puede ser un número negativo.");
        }

        CustomerResponseDTO customerResponseDTO;

        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isEmpty()) {
            throw new IllegalStateException("El registro con el id " + id + " no existe.");
        }

        customerResponseDTO = customerMapper.entityToResponseDto(optionalCustomer.get());

        return customerResponseDTO;
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

        Optional<Address> repeatedAddress = addressRepository.repeatedAddressValidation(
                address.getStreet(),
                address.getNumber(),
                address.getApartment(),
                address.getPostCode(),
                address.getCity(),
                address.getProvince(),
                address.getCountry()
        );

        if(repeatedAddress.isPresent()){
            customerToCreate.addAddress(repeatedAddress.get());
        }else{
            customerToCreate.addAddress(address);
        }

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
    public CustomerResponseDTO updateCustomer(Long id, CustomerDTO customerDTO) {

        Optional<Customer> optionalEntity = customerRepository.findById(id);

        if (optionalEntity.isEmpty()) {
            throw new RuntimeException("Error no existe el id de persona buscado");
        }

        Customer customerByDni = customerRepository.findByDni(customerDTO.getDni());

        if (!Objects.equals(customerDTO.getDni(), optionalEntity.get().getDni()) && customerByDni != null) {
            throw new BadRequestException("the client with the DNI entered already exists");
        }


        Customer customer = customerMapper.requestDtoToEntity(customerDTO);

        customer.setIdCustomer(id);
        customer.setCreatedDate(optionalEntity.get().getCreatedDate());

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
            throw new RuntimeException("Error no existe el id buscado");
        }

        customerRepository.delete(optionalCustomer.get());
        System.out.println("El cliente con el " + id + " fue eliminada correctamente.");

    }

}
