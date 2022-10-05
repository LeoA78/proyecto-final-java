package com.spring.app.repositories;

import com.spring.app.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IAddressRepository extends JpaRepository<Address, Long> {

    @Query("Select count(A) from Address A " +
            "where (A.street = :street) AND " +
            "(A.number = :number) AND " +
            "(A.apartment = :apartment) AND " +
            "(A.city = :city)")
    long repeatedAddressValidation(
            @Param("street") String street,
            @Param("number") String number,
            @Param("apartment") String apartment,
            @Param("city") String city);


}
