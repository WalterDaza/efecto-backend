package com.efecto.bar_efecto_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class    CategoryDTO {
    private Long id;
    private String nameCategory;
    private String image;
}
