package com.efecto.bar_efecto_backend.dto;

import java.math.BigDecimal;

public class ProductDTO {
    private Long id;
    private String nameProduct;
    private BigDecimal pricePurchase;
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

    public ProductDTO(Long id, String nameProduct, BigDecimal pricePurchase, BigDecimal priceSale, String image) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.pricePurchase = pricePurchase;
        this.priceSale = priceSale;
        this.image = image;
    }

    public ProductDTO() {
    }
}
