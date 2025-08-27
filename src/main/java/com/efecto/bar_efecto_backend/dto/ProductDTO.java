package com.efecto.bar_efecto_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    private String nameProduct;
    private BigDecimal pricePurchase;
    private BigDecimal priceSale;
    private String image;
    private Long categoryId;


}
