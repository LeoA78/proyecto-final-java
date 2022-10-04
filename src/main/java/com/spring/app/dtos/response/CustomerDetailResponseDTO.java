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
        value = "CustomerDetailResponseDTO",
        description = "Represents the data needed to created Customer Detail"
)
public class CustomerDetailResponseDTO implements Serializable {

    private Long idCustomerDetail;

    @ApiModelProperty(position = 1, notes = "Non null value, VIP is required.")
    private Boolean vip;

    @ApiModelProperty(position = 2, notes = "Non negative value, Credits are optional")
    private Integer credits;

}
