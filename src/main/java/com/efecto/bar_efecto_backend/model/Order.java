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
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn (name = "payment_method_id")
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "bar_table_id")
    private BarTable barTable;

    private String customer;

    private LocalDateTime dateOrder;

    private BigDecimal total;
}
