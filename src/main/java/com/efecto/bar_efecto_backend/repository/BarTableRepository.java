package com.efecto.bar_efecto_backend.repository;

import com.efecto.bar_efecto_backend.model.BarTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarTableRepository extends JpaRepository <BarTable, Long> {
}
