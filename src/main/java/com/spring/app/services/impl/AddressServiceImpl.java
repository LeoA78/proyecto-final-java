package com.spring.app.services.impl;

import com.spring.app.dtos.request.AddressDTO;
import com.spring.app.dtos.response.AddressResponseDTO;
import com.spring.app.entities.Address;
import com.spring.app.exceptions.NotFoundException;
import com.spring.app.mappers.IAddressMapper;
import com.spring.app.repositories.IAddressRepository;
import com.spring.app.services.IAddressService;
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
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private IAddressRepository addressRepository;

    @Autowired
    private IAddressMapper addressMapper;

    @Override
    public List<AddressResponseDTO> findAllAddresses(){
        List<AddressResponseDTO> addressResponseDTOS = new ArrayList<>();

        List<Address> addressList = addressRepository.findAll();

        for (Address address : addressList) {
            addressResponseDTOS.add(addressMapper.entityToResponseDto(address));
        }

        return addressResponseDTOS;
    }

    @Override
    public AddressResponseDTO findAddressById(Long id){
        AddressResponseDTO addressResponseDTO;

        Optional<Address> optionalAddress = addressRepository.findById(id);

        if(optionalAddress.isEmpty()){
            throw new IllegalStateException("El registro con el id " + id + " no existe.");
        }

        addressResponseDTO = addressMapper.entityToResponseDto(optionalAddress.get());

        return addressResponseDTO;
    }

    @Override
    public AddressResponseDTO addAddress(AddressDTO addressDTO){
        AddressResponseDTO addressResponseDTO;

        Address addressEntity = addressMapper.requestDtoToEntity(addressDTO);

        Address savedAddress = addressRepository.save(addressEntity);

        addressResponseDTO = addressMapper.entityToResponseDto(savedAddress);

        return addressResponseDTO;
    }

    @Override
    public AddressResponseDTO updateAddress(Long id, AddressDTO addressDTO){

        Optional<Address> optionalEntity = addressRepository.findById(id);

        if(optionalEntity.isEmpty()){
            throw new RuntimeException("Error no existe el id de persona buscado");
        }

        Address address = addressMapper.requestDtoToEntity(addressDTO);

        address.setIdAddress(id);

        Address updatedAddress = addressRepository.save(address);

        return addressMapper.entityToResponseDto(updatedAddress);
    }

    @Override
    public void deleteAddressById(Long id){
        Optional<Address> optionalAddress = addressRepository.findById(id);

        if(optionalAddress.isEmpty()) {
            throw new RuntimeException("Error no existe el id buscado");
        }

        addressRepository.delete(optionalAddress.get());
        System.out.println("La dirección con el " + id + " fue eliminada correctamente.");

    }



}
