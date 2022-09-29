package com.spring.app.entities;

import lombok.*;
import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "address")
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_address")
    private Long idAddress;

    @Column(name = "street", nullable = false, length = 30)
    private String street;

    @Column(name = "number", nullable = false)
    private Integer number;

    @Column(name = "apartment", length = 10)
    private String apartment;

    @Column(name = "apartment_number")
    private Integer apartmentNumber;

    @Column(name = "postcode", nullable = false, length = 10)
    private String postCode;

    @Column(name = "city", nullable = false, length = 30)
    private String city;

    @Column(name = "province", nullable = false, length = 30)
    private String province;

    @Column(name = "country", nullable = false, length = 30)
    private String country;

    @ManyToMany(mappedBy = "addressList")
    private List<Customer> customerList = new ArrayList<>();

}
