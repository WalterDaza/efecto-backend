package com.efecto.bar_efecto_backend.mapper;

import com.efecto.bar_efecto_backend.dto.ProductDTO;
import com.efecto.bar_efecto_backend.model.Category;
import com.efecto.bar_efecto_backend.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    // DTO → Entidad
    @Mapping(source = "categoryId", target = "category")
    Product toEntity(ProductDTO dto);

    // Entidad → DTO
    @Mapping(source = "category.id", target = "categoryId")
    ProductDTO toDTO(Product entity);

    // Metodo auxiliar para mapear de Long a Category
    default Category map(Long categoryId) {
        if (categoryId == null) {
            return null;
        }
        Category category = new Category();
        category.setId(categoryId);
        return category;
    }

    // Metodo auxiliar para mapear de Category a Long
    default Long map(Category category) {
        return category != null ? category.getId() : null;
    }
}
