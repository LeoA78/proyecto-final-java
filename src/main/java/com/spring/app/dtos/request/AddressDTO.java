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
        value = "AddressDTO",
        description = "Represents the data needed to created Address"
)
public class AddressDTO implements Serializable {

    @ApiModelProperty(position = 1, required = true, notes = "The street name is required.")
    @NotNull
    private String street;

    @ApiModelProperty(position = 2, required = true, notes = "The number is required.")
    @NotNull
    private String number;

    @ApiModelProperty(position = 3, notes = "The apartment is optional.")
    private String apartment;

    @ApiModelProperty(position = 4, required = true, notes = "The postal code is required.")
    @NotNull
    private String postCode;

    @ApiModelProperty(position = 5, required = true, notes = "The city is required.")
    @NotNull
    private String city;

    @ApiModelProperty(position = 6, required = true, notes = "The province is required.")
    @NotNull
    private String province;

    @ApiModelProperty(position = 7, required = true, notes = "The country is required.")
    @NotNull
    private String country;

}
