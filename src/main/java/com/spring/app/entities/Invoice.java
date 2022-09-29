package com.spring.app.entities;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "invoice")
public class Invoice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_invoice")
    private Long idInvoice;

    @Column(name = "invoice_type", nullable = false, length = 10)
    private String invoiceType;

    @Column(name = "description", nullable = false, length = 100)
    private String description;

    @Column(name = "total", nullable = false)
    private Double total;

    @Column(name = "createdDate", nullable = false)
    private LocalDate createdDate;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
