package com.spring.app.dtos.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;


import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
@ApiModel(
        value = "CustomerResponseDTO",
        description = "Represents the data needed to created Customer"
)
public class CustomerResponseDTO implements Serializable {

    private Long idCustomer;

    @ApiModelProperty(position = 1, notes = "The first name is required.")
    private String name;

    @ApiModelProperty(position = 2, notes = "The last name is required.")
    private String lastName;

    @ApiModelProperty(position = 3, notes = "DNI is required.")
    private String dni;

    @ApiModelProperty(position = 4, required = true, notes = "Non negative value, Date of Birth is required.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @ApiModelProperty(position = 5, notes = "Non negative value, Creation Date is required.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate createdDate;
}
