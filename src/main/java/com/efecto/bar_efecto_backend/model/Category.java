package com.efecto.bar_efecto_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name_category"})}
)
public class Category {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_category", nullable = false)
    private String nameCategory;

    private String image;

    @OneToMany(mappedBy = "category",
            cascade = CascadeType.ALL, //Esto le dice a JPA que las operaciones que hagas sobre Category (guardar, actualizar, eliminar...) también se aplicarán automáticamente a sus products.
            orphanRemoval = true) // Esto significa que si un producto es eliminado de la lista/set products de la categoría, y ya no pertenece a ninguna otra entidad, también se elimina de la base de datos automáticamente.
    private Set<Product> products = new HashSet<>();

}
