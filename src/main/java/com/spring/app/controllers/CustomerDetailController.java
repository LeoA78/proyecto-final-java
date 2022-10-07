package com.spring.app.controllers;

import com.spring.app.dtos.request.CustomerDetailDTO;
import com.spring.app.dtos.response.CustomerDetailResponseDTO;
import com.spring.app.services.ICustomerDetailService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Api(value = "Customer Detail Api", tags = {"Customer Detail Service"}) //Relacionado con Swagger
@RestController //Para que spring sepa que es un controlador REST
//Para que pueda ser accedido desde afuera de la api. Con controller solo podemos hacerlo de manera interna
@RequestMapping(value = "/customerDetail", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CustomerDetailController {

    private ICustomerDetailService customerDetailService;

    @GetMapping(value = "/all")
    @ApiOperation(
            value = "Retrieves all Lists Customer Details",
            httpMethod = "GET",
            response = CustomerDetailResponseDTO[].class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Body content with basic information about customerDetail details",
                    response = CustomerDetailResponseDTO[].class),
            @ApiResponse(
                    code = 400,
                    message = "Describes errors on invalid payload received, e.g: missing fields, invalid data formats, etc.")
    })
    public ResponseEntity<List<CustomerDetailResponseDTO>> getAllCustomerDetails() {

        List<CustomerDetailResponseDTO> customerDetailResponseDTOs = customerDetailService.findAllCustomerDetails();

        return new ResponseEntity<>(customerDetailResponseDTOs, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(
            value = "Retrieves all Lists Customer Details",
            httpMethod = "GET",
            response = CustomerDetailResponseDTO.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Body content with basic information about customer detail",
                    response = CustomerDetailResponseDTO.class),
            @ApiResponse(
                    code = 204,
                    message = "Body content is empty")
    })
    public ResponseEntity<CustomerDetailResponseDTO> getCustomerDetailById(
            @ApiParam(name = "id", required = true, value = "1", example = "1")
            @PathVariable("id") Long id) {

        CustomerDetailResponseDTO customerDetailResponseDTO = customerDetailService.findCustomerDetailById(id);


        return new ResponseEntity<>(customerDetailResponseDTO,HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(
            value = "Retrieves data associated to List Master by Id",
            httpMethod = "PUT",
            response = CustomerDetailResponseDTO.class
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
    public ResponseEntity<CustomerDetailResponseDTO> updateCustomerDetail(
            @ApiParam(name = "customerDetail", required = true, value = "Customer Detail")
            @RequestBody CustomerDetailDTO customerDetail,
            @ApiParam(name = "id", required = true, value = "Id", example = "1")
            @PathVariable("id") Long id){

        return new ResponseEntity<>(customerDetailService.updateCustomerDetail(id,customerDetail), HttpStatus.CREATED);
    }

}