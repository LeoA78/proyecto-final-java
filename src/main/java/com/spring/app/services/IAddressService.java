package com.spring.app.services;

import com.spring.app.dtos.request.AddressWithCustomerDniDTO;
import com.spring.app.dtos.response.AddressResponseDTO;

import java.util.List;

public interface IAddressService {


    List<AddressResponseDTO> findAllAddresses();

    AddressResponseDTO findAddressById(Long id);

    AddressResponseDTO addAddress(AddressWithCustomerDniDTO addressDTO);
    AddressResponseDTO updateAddress(Long id, AddressWithCustomerDniDTO addressDTO);

    void deleteAddressById(Long id);
}
