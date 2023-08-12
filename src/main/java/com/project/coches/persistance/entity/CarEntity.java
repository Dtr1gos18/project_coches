package com.project.coches.persistance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "coches")
public class CarEntity {

    @Id
    @Column(name = "codigo_coche")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codeCar;

    @Column(name = "marca_cocheid")
    private Integer brandCarId;

    @Column(name = "referencia")
    private String reference;

    @Column(name = "precio")
    private Double price;

    @Column(name = "anio_modelo")
    private Double modelYear;

    @Column(name = "color")
    private String color;

    @Column(name = "numero_caballos_fuerza")
    private Double horsePower;

    @Column(name = "cantidad_puertas")
    private Integer numberDoor;

    @Column(name = "cilindraje")
    private Double engineDisplacement;

    @Column(name = "transmision")
    private String transmission;

    @Column(name = "tipo_combustible")
    private String fuelType;

    @Column(name = "cantidad_asientos")
    private Integer numberSeat;

    @Column(name = "traccion")
    private Integer traction;

    @Column(name = "direccion")
    private String steering;

    @Column(name = "categoria")
    private String category;

    @Column(name = "ruta_imagen")
    private String imagePath;

    @ManyToOne
    @JoinColumn(name = "marca_cocheid", insertable = false, updatable = false)
    private BrandCarEntity brandCarEntity;

}
