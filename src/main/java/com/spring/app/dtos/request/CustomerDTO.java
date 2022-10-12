package com.spring.app.dtos.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDate;

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

    @ApiModelProperty(position = 1, required = true, notes = "The first name is required.")
    @NotNull
    private String name;

    @ApiModelProperty(position = 2, required = true, notes = "he last name is required.")
    @NotNull
    private String lastName;

    @ApiModelProperty(position = 3, required = true, notes = "DNI is required.")
    @NotNull
    @Pattern(regexp="^[0-9]{7,8}$",message="length must be 7 or 8 characters")
    private String dni;

    @ApiModelProperty(position = 4, required = true, notes = "Non negative value, Date of Birth is required.")
    @NotNull
    @Past(message = "The date is not valid")
    private LocalDate dateOfBirth;

    @ApiModelProperty(position = 5, required = true, notes = "Customer Detail is required.")
    @NotNull
    private CustomerDetailDTO detail;

    @ApiModelProperty(position = 6, required = true, notes = "Address is required.")
    @NotNull
    private AddressDTO address;

}
