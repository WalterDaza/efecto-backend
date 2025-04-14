package com.efecto.bar_efecto_backend.repository;

import com.efecto.bar_efecto_backend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository <Category, Long> {

}
