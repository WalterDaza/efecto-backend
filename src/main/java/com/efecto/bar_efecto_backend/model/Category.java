package com.efecto.bar_efecto_backend.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity

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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Category(Long id, String nameCategory, String image) {
        this.id = id;
        this.nameCategory = nameCategory;
        this.image = image;
    }

    public Category() {
    }
}
