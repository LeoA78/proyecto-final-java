package com.spring.app.services.impl;

import com.spring.app.dtos.request.CustomerDTO;
import com.spring.app.dtos.response.CustomerResponseDTO;
import com.spring.app.entities.Customer;
import com.spring.app.exceptions.BadRequestException;
import com.spring.app.mappers.ICustomerMapper;
import com.spring.app.repositories.ICustomerRepository;
import com.spring.app.services.ICustomerService;
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
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private ICustomerMapper customerMapper;

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
     * @return CustomerResponseDTO
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
     * This method adds a customer to the database and returns the added customer.
     *
     * @param customerDTO Customer Request DTO
     * @return CustomerResponseDTO
     */
    @Override
    public CustomerResponseDTO addCustomer(CustomerDTO customerDTO) {
        CustomerResponseDTO customerResponseDTO;

        Customer customerByDni = customerRepository.findByDni(customerDTO.getDni());

        if (customerByDni != null) {
            throw new BadRequestException("the client with the DNI entered already exists");
        }

        Customer customerEntity = customerMapper.requestDtoToEntity(customerDTO);
        customerEntity.setCreatedDate(LocalDate.now());

        Customer savedCustomer = customerRepository.save(customerEntity);

        customerResponseDTO = customerMapper.entityToResponseDto(savedCustomer);

        return customerResponseDTO;
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
     * @return void
     */
    @Override
    public void deleteCustomerById(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isEmpty()) {
            throw new RuntimeException("Error no existe el id buscado");
        }

        customerRepository.delete(optionalCustomer.get());
        System.out.println("La dirección con el " + id + " fue eliminada correctamente.");

    }

}
