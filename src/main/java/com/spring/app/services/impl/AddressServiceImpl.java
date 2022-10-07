package com.spring.app.services.impl;

import com.spring.app.dtos.request.AddressWithCustomerDniDTO;
import com.spring.app.dtos.response.AddressResponseDTO;
import com.spring.app.entities.Address;
import com.spring.app.entities.Customer;
import com.spring.app.exceptions.BadRequestException;
import com.spring.app.mappers.IAddressMapper;
import com.spring.app.repositories.IAddressRepository;
import com.spring.app.repositories.ICustomerRepository;
import com.spring.app.services.IAddressService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    private ICustomerRepository customerRepository;

    /**
     * This method return all addresses
     * @return List<AddressResponseDTO>
     */
    @Override
    public List<AddressResponseDTO> findAllAddresses(){
        List<AddressResponseDTO> addressResponseDTOS = new ArrayList<>();

        List<Address> addressList = addressRepository.findAll();

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
            throw new BadRequestException("El id no puede ser un número negativo.");
        }

        AddressResponseDTO addressResponseDTO;

        Optional<Address> optionalAddress = addressRepository.findById(id);

        if(optionalAddress.isEmpty()){
            throw new IllegalStateException("El registro con el id " + id + " no existe.");
        }

        addressResponseDTO = addressMapper.entityToResponseDto(optionalAddress.get());

        return addressResponseDTO;
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

        Customer customerByDni = customerRepository.findByDni(addressDTO.getCustomerDni());

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
                addressDTO.getCountry()
        );

        if(repeatedAddress.isPresent()) {

            //Si el cliente ya tiene esa dirección no permitirle repetirla
            for (Address addressCustomer : customerByDni.getAddressList()) {
                if(Objects.equals(addressCustomer.getIdAddress(), repeatedAddress.get().getIdAddress())){
                    throw new BadRequestException("The address is already associated with the customer");
                }
            }

            repeatedAddress.get().getCustomerList().add(customerByDni);
            customerByDni.getAddressList().add(repeatedAddress.get());
            createdAddress = addressRepository.save(repeatedAddress.get());
        } else {
            Address addressToCreate = addressMapper.requestDtoToEntity(addressDTO);
            addressToCreate.getCustomerList().add(customerByDni);
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
    public AddressResponseDTO updateAddress(Long id, AddressWithCustomerDniDTO addressDTO){

        Optional<Address> optionalEntity = addressRepository.findById(id);

        if(optionalEntity.isEmpty()){
            throw new RuntimeException("Error no existe el id de persona buscado");
        }

        Address address = addressMapper.requestDtoToEntity(addressDTO);

        address.setIdAddress(id);

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
            throw new RuntimeException("Error no existe el id buscado");
        }

        addressRepository.delete(optionalAddress.get());
        System.out.println("La dirección con el " + id + " fue eliminada correctamente.");

    }

}
