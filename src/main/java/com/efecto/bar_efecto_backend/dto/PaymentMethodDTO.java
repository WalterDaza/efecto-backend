package com.efecto.bar_efecto_backend.dto;

import com.efecto.bar_efecto_backend.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethodDTO {
    private Long id;
    private String nameMethodPayment;
}
