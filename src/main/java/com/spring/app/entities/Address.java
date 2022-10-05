package com.spring.app.entities;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@SQLDelete(sql = "UPDATE addresses SET deleted = true WHERE id_address=?")
@Where(clause = "deleted=false")
@Table(name = "addresses")
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_address")
    private Long idAddress;

    @Column(name = "street", nullable = false, length = 30)
    private String street;

    @Column(name = "number", nullable = false, length = 10)
    private String number;

    @Column(name = "apartment", length = 10)
    private String apartment;

    @Column(name = "postcode", nullable = false, length = 10)
    private String postCode;

    @Column(name = "city", nullable = false, length = 30)
    private String city;

    @Column(name = "province", nullable = false, length = 30)
    private String province;

    @Column(name = "country", nullable = false, length = 30)
    private String country;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    @ManyToMany(mappedBy = "addressList")
    private List<Customer> customerList = new ArrayList<>();

}
