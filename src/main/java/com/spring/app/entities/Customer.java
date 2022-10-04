package com.spring.app.entities;

import ch.qos.logback.classic.db.names.ColumnName;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@SQLDelete(sql = "UPDATE customers SET deleted = true WHERE id_customer=?")
@Where(clause = "deleted=false")
@Table(name = "customers") //AGREGAR QUE NO SE REPITA DNI
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_customer")
    private Long idCustomer;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "last_name", nullable = false, length = 30)
    private String lastName;

    @Column(name = "dni", nullable = false, length = 8)
    private String dni;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "createdDate", nullable = false)
    private LocalDate createdDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_detail_id")
    private CustomerDetail customerDetail;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    @ManyToMany
    @JoinTable(name = "tbl_customers_addresses",
        joinColumns = @JoinColumn(name = "customer_id"),
        inverseJoinColumns = @JoinColumn(name = "address_id")
    )
    private List<Address> addressList = new ArrayList<>();

    @OneToMany(mappedBy = "customer")
    private List<Invoice> invoiceList = new ArrayList<>();


}
