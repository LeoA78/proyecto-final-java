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
        value = "CustomerDTO",
        description = "Represents the data needed to created Customer"
)
public class CustomerResponseDTO implements Serializable {

    private Long idCustomer;

    @ApiModelProperty(position = 1, notes = "Non negative value, The first name is required.")
    private String name;

    @ApiModelProperty(position = 2, notes = "Non negative value, The last name is required.")
    private String lastName;

    @ApiModelProperty(position = 3, notes = "Non negative value, DNI is required.")
    private String dni;

    @ApiModelProperty(position = 4, notes = "Non negative value, age is required.")
    private Integer age;
}
