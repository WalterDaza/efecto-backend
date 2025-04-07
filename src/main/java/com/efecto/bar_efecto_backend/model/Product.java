package com.efecto.bar_efecto_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@Entity
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public BigDecimal getPricePurchase() {
        return pricePurchase;
    }

    public void setPricePurchase(BigDecimal pricePurchase) {
        this.pricePurchase = pricePurchase;
    }

    public BigDecimal getPriceSale() {
        return priceSale;
    }

    public void setPriceSale(BigDecimal priceSale) {
        this.priceSale = priceSale;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Product(Long id, String nameProduct, BigDecimal pricePurchase, BigDecimal priceSale, String image) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.pricePurchase = pricePurchase;
        this.priceSale = priceSale;
        this.image = image;
    }

    public Product() {
    }
}

