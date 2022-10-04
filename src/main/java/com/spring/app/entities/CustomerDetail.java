package com.spring.app.entities;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@SQLDelete(sql = "UPDATE customer_details SET deleted = true WHERE id_customer_detail=?")
@Where(clause = "deleted=false")
@Table(name = "customer_details")
public class CustomerDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_customer_detail")
    private Long idCustomerDetail;

    @Column(name = "vip", nullable = false)
    private Boolean vip;

    @Column(name = "credits", nullable = false)
    private Integer credits;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    @OneToOne(mappedBy = "customerDetail")
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
