package com.spring.app.controllers;


import com.spring.app.dtos.request.CustomerDTO;
import com.spring.app.dtos.request.CustomerUpdateDTO;
import com.spring.app.dtos.response.CustomerResponseDTO;
import com.spring.app.dtos.response.InvoiceWithoutCustomerResponseDTO;
import com.spring.app.services.ICustomerService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@Api(value = "Customer Api", tags = {"Customer Service"}) //Relacionado con Swagger
@RestController //Para que spring sepa que es un controlador REST
//Para que pueda ser accedido desde afuera de la api. Con controller solo podemos hacerlo de manera interna
@RequestMapping(value = "/customer", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CustomerController {

    private ICustomerService customerService;

    @GetMapping(value = "/all")
    @ApiOperation(
            value = "Retrieves all Lists Customers",
            httpMethod = "GET",
            response = CustomerResponseDTO[].class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Body content with information about an customer list",
                    response = CustomerResponseDTO[].class),
            @ApiResponse(
                    code = 404,
                    message = "Information about an customer list not found")
    })
    public ResponseEntity<List<CustomerResponseDTO>> getAllCustomers() {

        List<CustomerResponseDTO> customerResponseDTOs = customerService.findAllCustomers();

        return new ResponseEntity<>(customerResponseDTOs, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(
            value = "Retrieves all Lists Customers",
            httpMethod = "GET",
            response = CustomerResponseDTO.class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Body content with information about an customer",
                    response = CustomerResponseDTO.class),
            @ApiResponse(
                    code = 404,
                    message = "Information about an customer list not found")
    })
    public ResponseEntity<CustomerResponseDTO> getCustomerById(
            @ApiParam(name = "id", required = true, value = "1", example = "1")
            @PathVariable("id") Long id) {

        CustomerResponseDTO customerResponseDTO = customerService.findCustomerById(id);


        return new ResponseEntity<>(customerResponseDTO, HttpStatus.OK);
    }


    @GetMapping(value = "/invoices/{id}")
    @ApiOperation(
            value = "Retrieves all Customer Invoices",
            httpMethod = "GET",
            response = InvoiceWithoutCustomerResponseDTO[].class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Body content with information about an customer",
                    response = InvoiceWithoutCustomerResponseDTO.class),
            @ApiResponse(
                    code = 404,
                    message = "Information about an customer list not found")
    })
    public ResponseEntity<List<InvoiceWithoutCustomerResponseDTO>> getCustomerInvoicesById(
            @ApiParam(name = "id", required = true, value = "1", example = "1")
            @PathVariable("id") Long id) {

        List<InvoiceWithoutCustomerResponseDTO> customerInvoices = customerService.findAllInvoicesById(id);


        return new ResponseEntity<>(customerInvoices, HttpStatus.OK);
    }


    @PostMapping(value = "/add", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(
            value = "Retrieves data associated to List Master by Id",
            httpMethod = "POST",
            response = CustomerResponseDTO.class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 201,
                    message = "Body content with information about a successfully created customer",
                    response = CustomerResponseDTO.class),
            @ApiResponse(
                    code = 400,
                    message = "Information about an error creating a new customer")
    })
    public ResponseEntity<CustomerResponseDTO> addCustomerWithDetail(
            @ApiParam(name = "customer", required = true, value = "Customer")
            @Valid @RequestBody CustomerDTO customerDTO){

        return new ResponseEntity<>(customerService.addCustomer(customerDTO), HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(
            value = "Retrieves data associated to List Master by Id",
            httpMethod = "PUT",
            response = CustomerResponseDTO.class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 201,
                    message = "Body content with information about a successfully updated customer",
                    response = CustomerResponseDTO.class),
            @ApiResponse(
                    code = 400,
                    message = "Information about an error updating a new customer")
    })
    public ResponseEntity<CustomerResponseDTO> updateCustomer(
            @ApiParam(name = "id", required = true, value = "Id", example = "1")
            @PathVariable("id") Long id,
            @ApiParam(name = "customer", required = true, value = "Customer")
            @Valid @RequestBody CustomerUpdateDTO customer) {

        return new ResponseEntity<>(customerService.updateCustomer(id, customer), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ApiOperation(
            value = "Retrieves all Lists Customers",
            httpMethod = "DELETE"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 204,
                    message = "Body content about a successfully deleted customer"),
            @ApiResponse(
                    code = 400,
                    message = "Information about an error deleting a existing customer"),
            @ApiResponse(
                    code = 404,
                    message = "Information about a customer to delete not found")
    })
    public ResponseEntity<Void> deleteCustomerById(
            @ApiParam(name = "id", required = true, value = "id", example = "1")
            @PathVariable("id") Long id) {

        customerService.deleteCustomerById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}