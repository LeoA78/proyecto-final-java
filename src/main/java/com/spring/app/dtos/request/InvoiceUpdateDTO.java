package com.spring.app.dtos.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ApiModel(
        value = "InvoiceUpdateDTO",
        description = "Represents the data needed to created Invoice"
)
public class InvoiceUpdateDTO implements Serializable{

    @ApiModelProperty(position = 1, required = true, notes = "Invoice Type is required.")
    @NotNull
    @Pattern(regexp="^[aAbBcC$]",message="The type of invoice must be: A, B or C")
    private String invoiceType;

    @ApiModelProperty(position = 2, required = true, notes = "Description is required.")
    @NotNull
    private String description;

    @ApiModelProperty(position = 3, required = true, notes = "Non negative value, The total is required.")
    @NotNull
    private Double total;

    @ApiModelProperty(position = 4, required = true, notes = "Non negative value, Creation Date is required.")
    @NotNull
    private LocalDate createdDate;

}
