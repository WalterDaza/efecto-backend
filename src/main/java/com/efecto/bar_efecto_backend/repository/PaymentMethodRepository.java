package com.efecto.bar_efecto_backend.repository;

import com.efecto.bar_efecto_backend.model.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository extends JpaRepository <PaymentMethod, Long> {
}
