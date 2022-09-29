package com.spring.app.dtos.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
@ApiModel(
        value = "InvoiceDTO",
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
    private LocalDate createdDate;

}
