package com.spring.app.controllers;

import com.spring.app.dtos.request.AddressDTO;
import com.spring.app.dtos.response.AddressResponseDTO;
import com.spring.app.services.IAddressService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Api(value = "Address Api", tags = {"Address Service"}) //Relacionado con Swagger
@RestController //Para que spring sepa que es un controlador REST
//Para que pueda ser accedido desde afuera de la api. Con controller solo podemos hacerlo de manera interna
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
            @ApiResponse(code = 200,
                    message = "Body content with basic information about addresss",
                    response = AddressResponseDTO[].class),
            @ApiResponse(
                    code = 400,
                    message = "Describes errors on invalid payload received, e.g: missing fields, invalid data formats, etc.")
    })
    public ResponseEntity<List<AddressResponseDTO>> getAllAddresses() {

        List<AddressResponseDTO> addressResponseDTOs = addressService.findAllAddresses();

        return new ResponseEntity<>(addressResponseDTOs,HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(
            value = "Retrieves all Lists Addresss",
            httpMethod = "GET",
            response = AddressResponseDTO.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Body content with basic information about addresss",
                    response = AddressResponseDTO.class),
            @ApiResponse(
                    code = 204,
                    message = "Body content is empty")
    })
    public ResponseEntity<AddressResponseDTO> getAddressById(
            @ApiParam(name = "id", required = true, value = "1", example = "1")
            @PathVariable("id") Long id) {

        AddressResponseDTO addressResponseDTO = addressService.findAddressById(id);

        return new ResponseEntity<>(addressResponseDTO,HttpStatus.OK);
    }

    @DeleteMapping (value = "/delete/{id}")
    @ApiOperation(
            value = "Retrieves all Lists Addresss",
            httpMethod = "DELETE"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Body content with basic information about addresss",
                    response = AddressResponseDTO[].class),
            @ApiResponse(
                    code = 400,
                    message = "Describes errors on invalid payload received, e.g: missing fields, invalid data formats, etc.")
    })
    public ResponseEntity<Void> deleteAddressById(
            @ApiParam(name = "id", required = true, value = "id", example = "1")
            @PathVariable("id") Long id) {

        addressService.deleteAddressById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
                    message = "Body content with basic information for this Lists Master by Id"
            ),
            @ApiResponse(
                    code = 400,
                    message = "Describes errors on invalid payload received, e.g: missing fields, invalid data formats, etc.")
    })
    public ResponseEntity<AddressResponseDTO> addAddress(
            @ApiParam(name = "address", required = true, value = "Address")
            @RequestBody AddressDTO address) {

        //return ResponseEntity.ok(addressService.addAddress(address));
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
                    code = 200,
                    message = "Body content with basic information for this Lists Master by Id"
            ),
            @ApiResponse(
                    code = 400,
                    message = "Describes errors on invalid payload received, e.g: missing fields, invalid data formats, etc.")
    })
    public ResponseEntity<AddressResponseDTO> updateAddress(
            @ApiParam(name = "address", required = true, value = "Address")
            @RequestBody AddressDTO address,
            @ApiParam(name = "id", required = true, value = "Id", example = "1")
            @PathVariable("id") Long id){

        return new ResponseEntity<>(addressService.updateAddress(id,address), HttpStatus.CREATED);
    }


}
