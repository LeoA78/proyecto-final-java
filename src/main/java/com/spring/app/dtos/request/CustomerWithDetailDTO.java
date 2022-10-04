package com.spring.app.dtos.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ApiModel(
        value = "CustomerWithDetailDTO",
        description = "Represents the data needed to created an Customer With Detail"
)
public class CustomerWithDetailDTO implements Serializable {
    @ApiModelProperty(position = 1, required = true, notes = "Customer is required.")
    @NotNull
    @NotEmpty
    private CustomerDTO customer;

    @ApiModelProperty(position = 2, required = true, notes = "Customer Detail is required.")
    @NotNull
    @NotEmpty
    private CustomerDetailDTO detail;
}
