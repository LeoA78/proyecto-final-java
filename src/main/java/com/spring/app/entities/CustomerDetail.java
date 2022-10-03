package com.spring.app.entities;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
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

    @OneToOne(mappedBy = "customerDetail")
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
