package com.spring.app.dtos.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ApiModel(
        value = "InvoiceDTO",
        description = "Represents the data needed to created Invoice"
)
public class InvoiceDTO implements Serializable{

    @ApiModelProperty(position = 1, required = true, notes = "Non negative value, The total is required.")
    @NotNull
    private Double total;
}
