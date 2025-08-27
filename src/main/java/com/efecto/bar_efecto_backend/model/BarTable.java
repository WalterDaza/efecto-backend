package com.efecto.bar_efecto_backend.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BarTable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private int numberTable;

    @OneToMany(mappedBy = "barTableId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sale> sales;

}
