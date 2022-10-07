package com.spring.app.dtos.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ApiModel(
        value = "CustomerUpdateDTO",
        description = "Represents the data needed to created Customer"
)
public class CustomerUpdateDTO implements Serializable {

    @ApiModelProperty(position = 1, required = true, notes = "The first name is required.")
    @NotNull
    private String name;

    @ApiModelProperty(position = 2, required = true, notes = "he last name is required.")
    @NotNull
    private String lastName;

    @ApiModelProperty(position = 3, required = true, notes = "DNI is required.")
    @NotNull
    private String dni;

    @ApiModelProperty(position = 4, required = true, notes = "Non negative value, Date of Birth is required.")
    @NotNull
    private LocalDate dateOfBirth;

}
