package com.efecto.bar_efecto_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name_product"})}
)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Se basa en la estrategia de autoincremento de la base de datos.
    private Long id;

    @Column(name = "name_product", nullable = false) // no puede ser nula y no pueden repetirse los nombres
    private String nameProduct;

    @Column(name = "price_purchase", nullable = false, scale = 2)
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0") //Evitar precios negativos o nulos
    private BigDecimal pricePurchase;

    @Column(name = "price_sale", nullable = false, scale = 2)
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0") //Evitar precios negativos o nulos
    private BigDecimal priceSale;

    private String image;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductOrder> orders;

}

