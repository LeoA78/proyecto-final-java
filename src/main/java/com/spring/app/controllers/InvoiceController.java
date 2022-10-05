package com.spring.app.controllers;

import com.spring.app.dtos.request.FullInvoiceDTO;
import com.spring.app.dtos.request.InvoiceDTO;
import com.spring.app.dtos.response.FullInvoiceResponseDTO;
import com.spring.app.dtos.response.InvoiceResponseDTO;
import com.spring.app.services.IInvoiceService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Api(value = "Invoice Api", tags = {"Invoice Service"}) //Relacionado con Swagger
@RestController //Para que spring sepa que es un controlador REST
//Para que pueda ser accedido desde afuera de la api. Con controller solo podemos hacerlo de manera interna
@RequestMapping(value = "/invoice", produces = {MediaType.APPLICATION_JSON_VALUE})
public class InvoiceController {

    private IInvoiceService invoiceService;

    @GetMapping(value = "/all")
    @ApiOperation(
            value = "Retrieves all Lists Invoices",
            httpMethod = "GET",
            response = InvoiceResponseDTO[].class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Body content with basic information about invoices",
                    response = InvoiceResponseDTO[].class),
            @ApiResponse(
                    code = 400,
                    message = "Describes errors on invalid payload received, e.g: missing fields, invalid data formats, etc.")
    })
    public ResponseEntity<List<InvoiceResponseDTO>> getAllInvoices() {

        List<InvoiceResponseDTO> invoiceResponseDTOs = invoiceService.findAllInvoices();

        return new ResponseEntity<>(invoiceResponseDTOs, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(
            value = "Retrieves all Lists Invoices",
            httpMethod = "GET",
            response = InvoiceResponseDTO.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Body content with basic information about invoices",
                    response = InvoiceResponseDTO.class),
            @ApiResponse(
                    code = 204,
                    message = "Body content is empty")
    })
    public ResponseEntity<InvoiceResponseDTO> getInvoiceById(
            @ApiParam(name = "id", required = true, value = "1", example = "1")
            @PathVariable("id") Long id) {

        InvoiceResponseDTO invoiceResponseDTO = invoiceService.findInvoiceById(id);


        return new ResponseEntity<>(invoiceResponseDTO,HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ApiOperation(
            value = "Retrieves all Lists Invoices",
            httpMethod = "DELETE"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Body content with basic information about invoices",
                    response = InvoiceResponseDTO[].class),
            @ApiResponse(
                    code = 400,
                    message = "Describes errors on invalid payload received, e.g: missing fields, invalid data formats, etc.")
    })
    public ResponseEntity<Void> deleteInvoiceById(
            @ApiParam(name = "id", required = true, value = "id", example = "1")
            @PathVariable("id") Long id) {

        invoiceService.deleteInvoiceById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping(value = "/add", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(
            value = "Retrieves data associated to List Master by Id",
            httpMethod = "POST",
            response = FullInvoiceResponseDTO.class
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
    public ResponseEntity<FullInvoiceResponseDTO> addInvoice(
            @ApiParam(name = "invoice", required = true, value = "Invoice")
            @RequestBody FullInvoiceDTO fullInvoiceDTO) {

        return new ResponseEntity<>(invoiceService.addInvoice(fullInvoiceDTO), HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(
            value = "Retrieves data associated to List Master by Id",
            httpMethod = "PUT",
            response = InvoiceResponseDTO.class
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
    public ResponseEntity<InvoiceResponseDTO> updateInvoice(
            @ApiParam(name = "invoice", required = true, value = "Invoice")
            @RequestBody InvoiceDTO invoice,
            @ApiParam(name = "id", required = true, value = "Id", example = "1")
            @PathVariable("id") Long id){

        return new ResponseEntity<>(invoiceService.updateInvoice(id,invoice), HttpStatus.CREATED);
    }

}