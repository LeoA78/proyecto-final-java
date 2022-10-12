package com.spring.app.services.impl;

import com.spring.app.dtos.request.AddressDTO;
import com.spring.app.dtos.request.AddressWithCustomerDniDTO;
import com.spring.app.dtos.response.AddressResponseDTO;
import com.spring.app.dtos.response.CustomerResponseDTO;
import com.spring.app.entities.Address;
import com.spring.app.entities.Customer;
import com.spring.app.exceptions.customsExceptions.BadRequestException;
import com.spring.app.mappers.IAddressMapper;
import com.spring.app.mappers.ICustomerMapper;
import com.spring.app.repositories.IAddressRepository;
import com.spring.app.services.IAddressService;
import com.spring.app.services.ICustomerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

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

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private ICustomerMapper customerMapper;

    /**
     * This method return all addresses
     * @return List<AddressResponseDTO>
     */
    @Override
    public List<AddressResponseDTO> findAllAddresses(){
        List<AddressResponseDTO> addressResponseDTOS = new ArrayList<>();

        List<Address> addressList = addressRepository.findAll();

        if(addressList.isEmpty()){
            throw new BadRequestException("There are no records related to addresses");
        }

        for (Address address : addressList) {
            addressResponseDTOS.add(addressMapper.entityToResponseDto(address));
        }

        return addressResponseDTOS;
    }

    /**
     * This method return one address
     * @param id
     * @return AddressResponseDTO
     */
    @Override
    public AddressResponseDTO findAddressById(Long id){

        if(id < 0){
            throw new BadRequestException("the id cannot be a negative number. Request ID:" + id);
        }


        Optional<Address> optionalAddress = addressRepository.findById(id);

        if(optionalAddress.isEmpty()){
            throw new IllegalStateException("Record with id " + id + " does not exist.");
        }

        return addressMapper.entityToResponseDto(optionalAddress.get());
    }

    /**
     * This method adds an address to the database and returns the added address.
     * @param addressDTO Address Request DTO
     * @return AddressResponseDTO
     */
    @Override
    public AddressResponseDTO addAddress(AddressWithCustomerDniDTO addressDTO){
        Address createdAddress;

        if (ObjectUtils.isEmpty(addressDTO)) {
            throw new BadRequestException("Empty data in the entered entity");
        }
        CustomerResponseDTO customerResponse = customerService.findCustomerByDni(addressDTO.getCustomerDni());
        Customer customerByDni = customerMapper.responseDtoToEntity(customerResponse);

        if (customerByDni == null) {
            throw new BadRequestException("Cannot create an address without an associated customer");
        }


        Optional<Address> repeatedAddress = addressRepository.repeatedAddressValidation(
                addressDTO.getStreet(),
                addressDTO.getNumber(),
                addressDTO.getApartment(),
                addressDTO.getPostCode(),
                addressDTO.getCity(),
                addressDTO.getProvince(),
                addressDTO.getCountry(),
                customerByDni
        );
        System.out.println("Address repeated" + repeatedAddress);

        if(repeatedAddress.isPresent()) {
            throw new BadRequestException("The Address to add already exist");
        }else{
            Address addressToCreate = addressMapper.requestDtoToEntity(addressDTO);
            addressToCreate.setCustomer(customerByDni);
            customerByDni.getAddressList().add(addressToCreate);
            createdAddress = addressRepository.save(addressToCreate);
        }

        return addressMapper.entityToResponseDto(createdAddress);
    }

    /**
     * This method update an address in to the database and returns the modified address.
     * @param id Address id
     * @param addressDTO Address Request DTO
     * @return AddressResponseDTO
     */
    @Override
    public AddressResponseDTO updateAddress(Long id, AddressDTO addressDTO){

        Optional<Address> optionalAddress = addressRepository.findById(id);


        if(optionalAddress.isEmpty()){
            throw new RuntimeException("Address to update not found");
        }


        Optional<Address> addressToUpdate = addressRepository.repeatedAddressValidation(
                addressDTO.getStreet(),
                addressDTO.getNumber(),
                addressDTO.getApartment(),
                addressDTO.getPostCode(),
                addressDTO.getCity(),
                addressDTO.getProvince(),
                addressDTO.getCountry(),
                optionalAddress.get().getCustomer()
        );

        if(addressToUpdate.isPresent()){
            throw new RuntimeException("The Address to add already exist");
        }

        Address address = addressMapper.requestDtoToEntity(addressDTO);

        address.setIdAddress(id);
        address.setCustomer(optionalAddress.get().getCustomer()); //Le seteo el cliente a la dirección

        Address updatedAddress = addressRepository.save(address);

        return addressMapper.entityToResponseDto(updatedAddress);
    }

    /**
     * This method delete an address in to the database.
     * @param id Address id
     */
    @Override
    public void deleteAddressById(Long id){
        Optional<Address> optionalAddress = addressRepository.findById(id);

        if(optionalAddress.isEmpty()) {
            throw new RuntimeException("Address to delete not found");
        }

        addressRepository.delete(optionalAddress.get());
    }

}
