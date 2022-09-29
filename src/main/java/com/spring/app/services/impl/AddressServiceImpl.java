package com.spring.app.services.impl;

import com.spring.app.dtos.request.AddressDTO;
import com.spring.app.dtos.response.AddressResponseDTO;
import com.spring.app.mappers.IAddressMapper;
import com.spring.app.repositories.IAddressRepository;
import com.spring.app.services.IAddressService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service //Para que pueda ser inyectado desde otro lugar
@Slf4j
@Transactional //Hace el trabajo de JPA (commit - begin - rollback - etc)
public class AddressServiceImpl extends Exception implements IAddressService {

    @Autowired
    private IAddressRepository addressRepository;

    @Autowired
    private IAddressMapper addressMapper;

    @Override
    public List<AddressResponseDTO> findAllAddresses(){
        return null;
    }

    @Override
    public AddressResponseDTO findAddressById(Long id){
        return null;
    }

    @Override
    public AddressResponseDTO addAddress(AddressDTO addressDTO){
        return null;
    }

    @Override
    public AddressResponseDTO updateAddress(AddressDTO addressDTO){
        return null;
    }

    @Override
    public void deleteAddressById(Long id){

    }








}
