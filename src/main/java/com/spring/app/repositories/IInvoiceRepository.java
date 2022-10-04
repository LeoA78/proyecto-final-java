package com.spring.app.repositories;

import com.spring.app.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IInvoiceRepository extends JpaRepository<Invoice, Long> {
}
