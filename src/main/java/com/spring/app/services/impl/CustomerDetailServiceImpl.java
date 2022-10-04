package com.spring.app.services.impl;

import com.spring.app.dtos.request.CustomerDetailDTO;
import com.spring.app.dtos.response.CustomerDetailResponseDTO;
import com.spring.app.entities.CustomerDetail;
import com.spring.app.exceptions.BadRequestException;
import com.spring.app.mappers.ICustomerDetailMapper;
import com.spring.app.repositories.ICustomerDetailRepository;
import com.spring.app.services.ICustomerDetailService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service //Para que pueda ser inyectado desde otro lugar
@Transactional //Hace el trabajo de JPA (commit - begin - rollback - etc)
public class CustomerDetailServiceImpl implements ICustomerDetailService {

    @Autowired
    private ICustomerDetailRepository customerDetailRepository;

    @Autowired
    private ICustomerDetailMapper customerDetailMapper;

    /**
     * This method return all customerDetails
     *
     * @return List<CustomerDetailResponseDTO>
     */
    @Override
    public List<CustomerDetailResponseDTO> findAllCustomerDetails() {
        List<CustomerDetailResponseDTO> customerDetailResponseDTOS = new ArrayList<>();

        List<CustomerDetail> customerDetailList = customerDetailRepository.findAll();

        for (CustomerDetail customerDetail : customerDetailList) {
            customerDetailResponseDTOS.add(customerDetailMapper.entityToResponseDto(customerDetail));
        }

        return customerDetailResponseDTOS;
    }

    /**
     * This method return one customer Detail
     *
     * @param id
     * @return CustomerDetailResponseDTO
     */
    @Override
    public CustomerDetailResponseDTO findCustomerDetailById(Long id) {

        if (id < 0) {
            throw new BadRequestException("El id no puede ser un número negativo.");
        }

        CustomerDetailResponseDTO customerDetailResponseDTO;

        Optional<CustomerDetail> optionalCustomerDetail = customerDetailRepository.findById(id);

        if (optionalCustomerDetail.isEmpty()) {
            throw new IllegalStateException("El registro con el id " + id + " no existe.");
        }

        customerDetailResponseDTO = customerDetailMapper.entityToResponseDto(optionalCustomerDetail.get());

        return customerDetailResponseDTO;
    }

    /**
     * This method adds a customer Detail to the database and returns the added customer Detail.
     *
     * @param customerDetailDTO CustomerDetail Request DTO
     * @return CustomerDetailResponseDTO
     */
    @Override
    public CustomerDetailResponseDTO addCustomerDetail(CustomerDetailDTO customerDetailDTO) {
        CustomerDetailResponseDTO customerDetailResponseDTO;

        CustomerDetail customerDetailEntity = customerDetailMapper.requestDtoToEntity(customerDetailDTO);


        CustomerDetail savedCustomerDetail = customerDetailRepository.save(customerDetailEntity);

        customerDetailResponseDTO = customerDetailMapper.entityToResponseDto(savedCustomerDetail);

        return customerDetailResponseDTO;
    }

    /**
     * This method update a customer Detail in to the database and returns the modified customer Detail.
     *
     * @param id          CustomerDetail id
     * @param customerDetailDTO CustomerDetail Request DTO
     * @return CustomerDetailResponseDTO
     */
    @Override
    public CustomerDetailResponseDTO updateCustomerDetail(Long id, CustomerDetailDTO customerDetailDTO) {

        Optional<CustomerDetail> optionalEntity = customerDetailRepository.findById(id);

        if (optionalEntity.isEmpty()) {
            throw new RuntimeException("Error no existe el id de persona buscado");
        }


        CustomerDetail customerDetail = customerDetailMapper.requestDtoToEntity(customerDetailDTO);

        customerDetail.setIdCustomerDetail(id);

        CustomerDetail updatedCustomerDetail = customerDetailRepository.save(customerDetail);

        return customerDetailMapper.entityToResponseDto(updatedCustomerDetail);
    }

    /**
     * This method delete a customerDetail in to the database.
     *
     * @param id CustomerDetail id
     * @return void
     */
    @Override
    public void deleteCustomerDetailById(Long id) {
        Optional<CustomerDetail> optionalCustomerDetail = customerDetailRepository.findById(id);

        if (optionalCustomerDetail.isEmpty()) {
            throw new RuntimeException("Error no existe el id buscado");
        }

        customerDetailRepository.delete(optionalCustomerDetail.get());
        System.out.println("La dirección con el " + id + " fue eliminada correctamente.");

    }

}

