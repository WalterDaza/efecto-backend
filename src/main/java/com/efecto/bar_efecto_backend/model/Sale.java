package com.efecto.bar_efecto_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sale {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateSale;

    private BigDecimal totalSale;

    private boolean state;

    private BigDecimal outstandingBalance;

    @ManyToOne
    @JoinColumn(name = "bar_table_id")
    private BarTable barTableId;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;

}
