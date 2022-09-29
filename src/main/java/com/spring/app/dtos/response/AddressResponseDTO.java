package com.spring.app.dtos.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
@ApiModel(
        value = "AddressResponseDTO",
        description = "Represents the data needed to created Address"
)
public class AddressResponseDTO implements Serializable {

    private Long idAddress;

    @ApiModelProperty(position = 1, notes = "Non negative value, The street name is required.")
    private String street;

    @ApiModelProperty(position = 2, notes = "Non negative value, The number is required.")
    private Integer number;

    @ApiModelProperty(position = 3, notes = "Non negative value, The apartment is optional.")
    private String apartment;

    @ApiModelProperty(position = 4, notes = "Non negative value, The apartment number is optional.")
    private Integer apartmentNumber;

    @ApiModelProperty(position = 5, notes = "Non negative value, The postal code is required.")
    private Integer postCode;

    @ApiModelProperty(position = 6, notes = "Non negative value, The city is required.")
    private String city;

    @ApiModelProperty(position = 7, notes = "Non negative value, The province is required.")
    private String province;

    @ApiModelProperty(position = 8, notes = "Non negative value, The country is required.")
    private String country;
}
