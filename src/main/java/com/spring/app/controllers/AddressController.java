package com.spring.app.controllers;

import com.spring.app.dtos.request.AddressDTO;
import com.spring.app.dtos.request.AddressWithCustomerDniDTO;
import com.spring.app.dtos.response.AddressResponseDTO;
import com.spring.app.services.IAddressService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@Api(value = "Address Api", tags = {"Address Service"})
@RestController
@RequestMapping(value = "/address", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AddressController {

    private IAddressService addressService;

    @GetMapping(value = "/all")
    @ApiOperation(
            value = "Retrieves all Lists Addresses",
            httpMethod = "GET",
            response = AddressResponseDTO[].class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Body content with information about an address list",
                    response = AddressResponseDTO[].class),
            @ApiResponse(
                    code = 404,
                    message = "Information about an address list not found")
    })
    public ResponseEntity<List<AddressResponseDTO>> getAllAddresses() {

        List<AddressResponseDTO> addressResponseDTOs = addressService.findAllAddresses();

        return new ResponseEntity<>(addressResponseDTOs,HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(
            value = "Retrieves all Lists Addresses",
            httpMethod = "GET",
            response = AddressResponseDTO.class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Body content with information about an address",
                    response = AddressResponseDTO.class),
            @ApiResponse(
                    code = 404,
                    message = "Information about an address list not found")
    })
    public ResponseEntity<AddressResponseDTO> getAddressById(
            @ApiParam(name = "id", required = true, value = "1", example = "1")
            @PathVariable("id") Long id) {

        AddressResponseDTO addressResponseDTO = addressService.findAddressById(id);


        return new ResponseEntity<>(addressResponseDTO,HttpStatus.OK);
    }

    @PostMapping(value = "/add", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(
            value = "Retrieves data associated to List Master by Id",
            httpMethod = "POST",
            response = AddressResponseDTO.class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 201,
                    message = "Body content with information about a successfully created address",
                    response = AddressResponseDTO.class),
            @ApiResponse(
                    code = 400,
                    message = "Information about an error creating a new address")
    })
    public ResponseEntity<AddressResponseDTO> addAddress(
            @ApiParam(name = "address", required = true, value = "Address")
            @Valid @RequestBody AddressWithCustomerDniDTO address) {

        return new ResponseEntity<>(addressService.addAddress(address), HttpStatus.CREATED);
    }


    @PutMapping(value = "/update/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(
            value = "Retrieves data associated to List Master by Id",
            httpMethod = "PUT",
            response = AddressResponseDTO.class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 201,
                    message = "Body content with information about a successfully updated address",
                    response = AddressResponseDTO.class),
            @ApiResponse(
                    code = 400,
                    message = "Information about an error updating a new address")
    })
    public ResponseEntity<AddressResponseDTO> updateAddress(
            @ApiParam(name = "id", required = true, value = "Id", example = "1")
            @PathVariable("id") Long id,
            @ApiParam(name = "address", required = true, value = "Address")
            @Valid @RequestBody AddressDTO address
            ){

        return new ResponseEntity<>(addressService.updateAddress(id,address), HttpStatus.CREATED);
    }


    @DeleteMapping (value = "/delete/{id}")
    @ApiOperation(
            value = "Retrieves all Lists Addresses",
            httpMethod = "DELETE"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 204,
                    message = "Body content about a successfully deleted address"),
            @ApiResponse(
                    code = 400,
                    message = "Information about an error deleting a existing address"),
            @ApiResponse(
                    code = 404,
                    message = "Information about an address to delete not found")
    })
    public ResponseEntity<Void> deleteAddressById(
            @ApiParam(name = "id", required = true, value = "id", example = "1")
            @PathVariable("id") Long id) {

        addressService.deleteAddressById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
