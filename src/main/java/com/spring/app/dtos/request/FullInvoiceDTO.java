package com.spring.app.dtos.request;

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
@Setter
@ApiModel(
        value = "FullInvoiceDTO",
        description = "Represents the data needed to created Invoice"
)
public class FullInvoiceDTO implements Serializable{

    @ApiModelProperty(position = 1, required = true, notes = "Invoice is required")
    @NotNull
    private InvoiceDTO invoice;

    @ApiModelProperty(position = 2, required = true, notes = "Non negative value, Customer DNI is required.")
    @NotNull
    private String customerDni;

}
