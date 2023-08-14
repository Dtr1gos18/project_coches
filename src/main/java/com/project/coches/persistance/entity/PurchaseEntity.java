package com.project.coches.persistance.entity;

import com.project.coches.domain.dto.CarPurchaseRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@Entity
@Table(name = "compras")
public class PurchaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_factura")
    private Integer numberBill;

    @Column(name = "clientecedula")
    private String cardIdCustomer;

    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime date;

    private Double total;

    @Column(name = "medio_pago")
    private String paymentMethod;

    @OneToMany(mappedBy = "purchaseEntity", cascade = {CascadeType.ALL})
    private List<CarPurchaseEntity> carPurchaseEntities;

    @ManyToOne
    @JoinColumn(name = "clientecedula", insertable = false, updatable = false)
    private CustomerEntity customerEntity;
}
