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
        value = "CustomerDetailDTO",
        description = "Represents the data needed to created Customer Detail"
)
public class CustomerDetailDTO implements Serializable {

    @ApiModelProperty(position = 1, required = true, notes = "Non null value, VIP is required.")
    @NotNull
    private Boolean vip;

    @ApiModelProperty(position = 2, notes = "Non negative value, Credits are optional")
    private Integer credits;
}
