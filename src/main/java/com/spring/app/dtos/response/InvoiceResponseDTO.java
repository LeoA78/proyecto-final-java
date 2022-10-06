package com.spring.app.dtos.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
@ApiModel(
        value = "InvoiceResponseDTO",
        description = "Represents the data needed to created Invoice"
)
public class InvoiceResponseDTO implements Serializable {

    private Long idInvoice;

    @ApiModelProperty(position = 1, notes = "Invoice type is required.")
    private String invoiceType;

    @ApiModelProperty(position = 2, notes = "Description is required.")
    private String description;

    @ApiModelProperty(position = 3, notes = "Non negative value, The total is required.")
    private Double total;

    @ApiModelProperty(position = 4, notes = "Non negative value, Creation Date is required.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate createdDate;

    @ApiModelProperty(position = 5, notes = "Customer is required.")
    private CustomerResponseDTO customer;

}
