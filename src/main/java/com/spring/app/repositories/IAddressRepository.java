package com.spring.app.repositories;

import com.spring.app.entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAddressRepository extends JpaRepository<AddressEntity, Long> {
}
