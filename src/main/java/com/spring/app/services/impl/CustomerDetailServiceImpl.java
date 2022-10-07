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
            throw new BadRequestException("the id cannot be a negative number. Request ID:" + id);
        }

        Optional<CustomerDetail> optionalCustomerDetail = customerDetailRepository.findById(id);

        if (optionalCustomerDetail.isEmpty()) {
            throw new IllegalStateException("Record with id " + id + " does not exist.");
        }

        return customerDetailMapper.entityToResponseDto(optionalCustomerDetail.get());
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
            throw new RuntimeException("Record with id " + id + " does not exist.");
        }

        CustomerDetail customerDetailToUpdate = customerDetailMapper.requestDtoToEntity(customerDetailDTO);

        customerDetailToUpdate.setIdCustomerDetail(id);

        CustomerDetail updatedCustomerDetail = customerDetailRepository.save(customerDetailToUpdate);

        return customerDetailMapper.entityToResponseDto(updatedCustomerDetail);
    }


}

