package com.spring.app.services;

import com.spring.app.dtos.request.AddressDTO;
import com.spring.app.dtos.response.AddressResponseDTO;

import java.util.List;

public interface IAddressService {


    List<AddressResponseDTO> findAllAddresses();

    AddressResponseDTO findAddressById(Long id);

    AddressResponseDTO addAddress(AddressDTO addressDTO);

    AddressResponseDTO updateAddress(AddressDTO addressDTO);

    void deleteAddressById(Long id);
}
