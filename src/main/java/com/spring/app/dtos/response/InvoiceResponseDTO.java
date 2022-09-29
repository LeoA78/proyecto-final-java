package com.spring.app.dtos.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

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

    @ApiModelProperty(position = 1, notes = "Non negative value, The total is required.")
    private Double total;

}
