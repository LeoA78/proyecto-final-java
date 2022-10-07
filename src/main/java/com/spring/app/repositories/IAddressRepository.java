package com.spring.app.repositories;

import com.spring.app.entities.Address;
import com.spring.app.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IAddressRepository extends JpaRepository<Address, Long> {

    @Query("Select A from Address A " +
            "where (A.street = :street) AND " +
            "(A.number = :number) AND " +
            "(A.apartment = :apartment) AND " +
            "(A.postCode = :postCode) AND "+
            "(A.city = :city) AND " +
            "(A.province = :province) AND" +
            "(A.country = :country) AND" +
            "(A.customer = :customer)")
    Optional<Address> repeatedAddressValidation(
            @Param("street") String street,
            @Param("number") String number,
            @Param("apartment") String apartment,
            @Param("postCode") String postCode,
            @Param("city") String city,
            @Param("province") String province,
            @Param("country") String country,
            @Param("customer") Customer customer);



}
