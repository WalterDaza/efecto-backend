package com.efecto.bar_efecto_backend.repository;

import com.efecto.bar_efecto_backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository <Product, Long> {

}
