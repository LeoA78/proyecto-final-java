package com.spring.app.dtos.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ApiModel(
        value = "CustomerDTO",
        description = "Represents the data needed to created Customer"
)
public class CustomerDTO implements Serializable {

    @ApiModelProperty(position = 1, required = true, notes = "Non negative value, The first name is required.")
    @NotNull
    private String name;

    @ApiModelProperty(position = 2, required = true, notes = "Non negative value, The last name is required.")
    @NotNull
    private String lastName;

    @ApiModelProperty(position = 3, required = true, notes = "Non negative value, DNI is required.")
    @NotNull
    private String dni;

    @ApiModelProperty(position = 4, required = true, notes = "Non negative value, age is required.")
    @NotNull
    private Integer age;
}
