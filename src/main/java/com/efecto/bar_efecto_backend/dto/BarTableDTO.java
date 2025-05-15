package com.efecto.bar_efecto_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BarTableDTO {
    private Long id;
    private String type;
    private int numberTable;
}
