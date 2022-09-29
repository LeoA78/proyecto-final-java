package com.spring.app.dtos.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    @ApiModelProperty(position = 1, required = true, notes = "Non negative value, The street name is required.")
    @NotNull
    private String street;

    @ApiModelProperty(position = 2, required = true, notes = "Non negative value, The number is required.")
    @NotNull
    private Integer number;

    @ApiModelProperty(position = 3, notes = "Non negative value, The apartment is optional.")
    private String apartment;

    @ApiModelProperty(position = 4, notes = "Non negative value, The apartment number is optional.")
    private Integer apartmentNumber;

    @ApiModelProperty(position = 5, required = true, notes = "Non negative value, The postal code is required.")
    @NotNull
    private Integer postCode;

    @ApiModelProperty(position = 6, required = true, notes = "Non negative value, The city is required.")
    @NotNull
    private String city;

    @ApiModelProperty(position = 7, required = true, notes = "Non negative value, The province is required.")
    @NotNull
    private String province;

    @ApiModelProperty(position = 8, required = true, notes = "Non negative value, The country is required.")
    @NotNull
    private String country;

}
